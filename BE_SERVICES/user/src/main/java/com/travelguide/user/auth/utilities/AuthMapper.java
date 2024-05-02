package com.travelguide.user.auth.utilities;

import com.travelguide.user.auth.domain.dto.User;
import com.travelguide.user.auth.domain.dto.UserDetails;

public class AuthMapper {
  public static UserDetails convertToUserDetails(User user) {
    return UserDetails.builder()
        .userId(user.getUserId())
        .firstName(user.getFirstName())
        .middleName(user.getMiddleName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .mobileNo(user.getMobileNo())
        .mfa(user.isMfa())
        .build();
  }
}
