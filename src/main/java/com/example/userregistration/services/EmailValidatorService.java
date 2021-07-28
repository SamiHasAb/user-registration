package com.example.userregistration.services;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
@Service
public class EmailValidatorService implements Predicate<String> {


    @Override
    public boolean test(String s) {

        // we normally use Regex to validate email

        return true;
    }
}
