package com.example.userregistration.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Slf4j
@AllArgsConstructor
@Service
public class EmailSenderService {

    private final JavaMailSender mailSender;


    @Async
    public void sendConfirmationLink(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setFrom("spring.boot.conformationmail.test@gmail.com");
            helper.setSubject("Confirm your email");

            helper.setTo(to);
            helper.setText(email, true);

            mailSender.send(mimeMessage);
            log.debug("Mail Sent.......");


        } catch (MessagingException e) {
            log.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }






}
