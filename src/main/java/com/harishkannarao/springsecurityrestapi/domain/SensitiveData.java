package com.harishkannarao.springsecurityrestapi.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class SensitiveData {
    private final String firstName;
    private final String lastName;
}
