package com.travelguide.user.auth.service;

import com.travelguide.user.auth.UserRepository;
import com.travelguide.user.auth.domain.dto.UserDetails;
import com.travelguide.user.auth.utilities.AuthMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class AuthService {
    private final UserRepository userRepository;
    public AuthService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public Mono<UserDetails> getUserDetails(UserDetails userDetails){
        return userRepository.findById(userDetails.getUserId())
                .map(AuthMapper::convertToUserDetails)
                .defaultIfEmpty(UserDetails.builder().build());
    }
}
