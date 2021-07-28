package com.example.userregistration.services;


import com.example.userregistration.models.ConfirmationToken;
import com.example.userregistration.models.RegistrationRequestDto;
import com.example.userregistration.models.UserAccount;
import com.example.userregistration.models.UserAccountRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserAccountService userAccountService;
    private final EmailValidatorService emailValidatorService;
    private final ConfirmationTokenService confirmationTokenService;
//    private final EmailSender emailSender;

    public String register(RegistrationRequestDto request) {

        boolean isValidEmail = emailValidatorService.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }


        // register the user and return a token

        return userAccountService.signUpUser(new UserAccount(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                UserAccountRole.USER));
    }

    @Transactional
    public String confirmToken(String token) {

        // check if there is a token
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        // if email already confirmed
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        // if token expired
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        // set confirm time
        confirmationTokenService.setConfirmedAt(token);

        userAccountService.enableAppUser(confirmationToken.getUserAccount().getEmail());

        return "Your account has been confirmed";

    }

}
