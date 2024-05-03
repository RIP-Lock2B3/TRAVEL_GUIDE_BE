package com.travelguide.user.auth.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {
  MANDATORY_FIELDS_MISSING(422, "MANDATORY_FIELDS_MISSING", "MANDATORY FIELDS MISSING IN PAYLOAD"),
  LOGIN_FAILED(422, "INCORRECT_LOGIN_CREDENTIALS", "INCORRECT USERNAME/PASSWORD");
  private final int httpStatusCode;
  private final String messageCode;
  private final String messageDescription;

  ErrorMessage(int httpStatusCode, String messageCode, String messageDescription) {
    this.httpStatusCode = httpStatusCode;
    this.messageCode = messageCode;
    this.messageDescription = messageDescription;
  }
}
