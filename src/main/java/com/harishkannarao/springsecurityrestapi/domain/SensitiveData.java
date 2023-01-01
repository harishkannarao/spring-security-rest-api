package com.harishkannarao.springsecurityrestapi.domain;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class SensitiveData {
    String firstName;
    String lastName;
}
