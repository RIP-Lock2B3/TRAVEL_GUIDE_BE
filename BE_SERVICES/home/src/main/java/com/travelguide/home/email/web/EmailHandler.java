package com.travelguide.home.email.web;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.travelguide.home.email.domain.EmailDetails;
import com.travelguide.home.email.service.EmailService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class EmailHandler implements HandlerFunction<ServerResponse> {
  private final EmailService emailService;

  public EmailHandler(EmailService emailService) {
    this.emailService = emailService;
  }

  @Override
  public Mono<ServerResponse> handle(ServerRequest request) {
    return request
        .bodyToMono(EmailDetails.class)
        .map(emailService::sendEmail)
        .flatMap(s -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(s));
  }

  public Mono<ServerResponse> handleEmailWithAttachments(ServerRequest request) {
    return request
        .bodyToMono(EmailDetails.class)
        .map(emailService::sendEmailWithAttachment)
        .flatMap(s -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(s));
  }
}
