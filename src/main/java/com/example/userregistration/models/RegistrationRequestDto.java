package com.example.userregistration.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequestDto {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
