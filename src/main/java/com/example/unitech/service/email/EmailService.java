package com.example.unitech.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

        private final JavaMailSender mailSender;

        public void sendEmail(String email, String subject, String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setText(text);
            message.setSubject(subject);
            message.setTo(email);
            message.setFrom("cavidabdullayevv20@gmail.com");
            mailSender.send(message);
            System.out.println("end");
    }

    public  void forgetPassword(String to, String subject, String password) throws MessagingException {
        MimeMessage message= mailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(message,true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText("Your password: "+password);

        mailSender.send(message);
    }

}