package com.example.userregistration.web;

import com.example.userregistration.models.RegistrationRequestDto;
import com.example.userregistration.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequestDto request) {

        return registrationService.register(request);
    }

//    @GetMapping(path = "confirm")
//    public String confirm(@RequestParam("token") String token) {
//
//        return registrationService.confirmToken(token);
//    }
}
