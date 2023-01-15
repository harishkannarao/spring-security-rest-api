package com.harishkannarao.springsecurityrestapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import java.util.function.Consumer;

@Configuration
public class AdditionalConfiguration {

    @Bean
    public Consumer<HttpSecurity> testEndpointCustomizer() {
        return http -> {
            try {
                http.authorizeHttpRequests((auth) -> auth
                                .requestMatchers("/test-endpoint").permitAll());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
