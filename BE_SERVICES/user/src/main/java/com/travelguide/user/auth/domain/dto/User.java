package com.travelguide.user.auth.domain.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_details")
@Getter
@Entity
public class User {
    @Id
    @org.springframework.data.annotation.Id
    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobileNo;
    private boolean mfa;
}
