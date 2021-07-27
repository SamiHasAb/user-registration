package com.example.userregistration.services;

import com.example.userregistration.models.RegistrationRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserAccountService userAccountService;
//    private final EmailValidator emailValidator;
//    private final ConfirmationTokenService confirmationTokenService;
//    private final EmailSender emailSender;

    public String register(RegistrationRequestDto request) {
        return "works";
    }

}
