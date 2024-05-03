package com.travelguide.user.auth.service;

import static com.travelguide.user.auth.utilities.AuthMapper.convertToUser;

import com.travelguide.user.auth.constants.Status;
import com.travelguide.user.auth.domain.dto.UserDetails;
import com.travelguide.user.auth.exception.AuthException;
import com.travelguide.user.auth.exception.ErrorMessage;
import com.travelguide.user.auth.repository.UserRepository;
import com.travelguide.user.auth.utilities.AuthMapper;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService {
  private final UserRepository userRepository;

  @Value("${specialkey.cipher}")
  private String SPECIAL_KEY;

  public AuthService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public static String encrypt(String data, String key) {
    try {
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
      cipher.init(Cipher.ENCRYPT_MODE, keySpec);
      return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  public static String decrypt(byte[] encryptedData, String key) {
    try {
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
      cipher.init(Cipher.DECRYPT_MODE, keySpec);
      return new String(cipher.doFinal(encryptedData));
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  public Mono<UserDetails> getUserDetails(UserDetails userDetails) {
    return userRepository
        .findByUserId(userDetails.getUserId())
        .flatMap(
            user -> {
              if (StringUtils.equals(
                  userDetails.getPassword(),
                  decrypt(Base64.getDecoder().decode(user.getPassword()), SPECIAL_KEY))) {
                return Mono.just(user);
              }
              return Mono.error(new AuthException(ErrorMessage.LOGIN_FAILED));
            })
        .map(AuthMapper::convertToUserDetails)
        .defaultIfEmpty(UserDetails.builder().build());
  }

  public Mono<UserDetails> createUser(UserDetails userDetails) {
    userDetails.setPassword(encrypt(userDetails.getPassword(), SPECIAL_KEY));
    return userRepository
        .save(convertToUser(userDetails))
        .map(AuthMapper::convertToUserDetails)
        .defaultIfEmpty(UserDetails.builder().build());
  }

  public Mono<UserDetails> validateUserId(UserDetails userDetails) {
    return userRepository
        .countByUserId(userDetails.getUserId())
        .map(n -> n > 0 ? Status.NOT_AVAILABLE.name() : Status.AVAILABLE.name())
        .map(userIdStatus -> UserDetails.builder().userIdStatus(userIdStatus).build());
  }
}
