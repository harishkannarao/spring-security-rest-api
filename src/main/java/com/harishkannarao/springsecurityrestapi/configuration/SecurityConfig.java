package com.harishkannarao.springsecurityrestapi.configuration;

import com.harishkannarao.springsecurityrestapi.security.filter.CustomAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationFilter customAuthenticationFilter;

    @Autowired(required = false)
    private List<Consumer<HttpSecurity>> httpSecurityCustomizers;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        Optional.ofNullable(httpSecurityCustomizers)
                .ifPresent(value -> value.forEach(consumer -> consumer.accept(http)));

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests((auth) ->
                        auth
                                .requestMatchers("/general-data").permitAll()
                                .requestMatchers("/user-data").hasAuthority("ROLE_USER")
                                .requestMatchers("/admin/get-user-data/*").hasAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated()
                )
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> response.setStatus(HttpStatus.FORBIDDEN.value()))
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(@Value("${cors.origin.patterns}") String originPatterns) {
        List<String> originPatternList = Stream.of(originPatterns.split(",")).toList();
        List<String> methods = List.of("GET", "PUT", "POST", "DELETE", "OPTIONS", "PATCH", "TRACE");
        String urlPattern = "/**";
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(methods);
        configuration.setAllowedOriginPatterns(originPatternList);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(urlPattern, configuration);
        return source;
    }
}
