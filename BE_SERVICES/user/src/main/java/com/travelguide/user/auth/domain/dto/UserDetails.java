package com.travelguide.user.auth.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class UserDetails {
    private String firstName;
    private String middleName;
    private String lastName;
    private String userId;
    private String email;
    private String mobileNo;
    private boolean mfa;
}
