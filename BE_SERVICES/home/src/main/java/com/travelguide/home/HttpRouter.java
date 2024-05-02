package com.travelguide.home;

import static com.travelguide.home.email.constants.EmailConstants.NOTIFY_USER_URI;
import static com.travelguide.home.email.constants.EmailConstants.NOTIFY_USER_WITH_ATT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.travelguide.home.email.web.EmailHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HttpRouter {
  private final EmailHandler emailHandler;

  public HttpRouter(EmailHandler emailHandler) {
    this.emailHandler = emailHandler;
  }

  @Bean
  RouterFunction<ServerResponse> routes() {
    return route()
        .POST(NOTIFY_USER_URI, RequestPredicates.accept(MediaType.APPLICATION_JSON), emailHandler)
        .POST(
            NOTIFY_USER_WITH_ATT,
            RequestPredicates.accept(MediaType.APPLICATION_JSON),
            emailHandler::handleEmailWithAttachments)
        .build();
  }
}
