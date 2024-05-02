package com.travelguide.home.email.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    String recipient;
    String msgBody;
    String subject;
    String attachment;
}
