package com.sanketh.AIChatBot.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    JavaMailSender mailSender = new JavaMailSenderImpl();
    public void sendmail(String to, String subject, String content)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sankeths908@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);

    }
}
