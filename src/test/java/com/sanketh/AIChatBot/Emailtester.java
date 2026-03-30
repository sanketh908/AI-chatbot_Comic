package com.sanketh.AIChatBot;

import com.sanketh.AIChatBot.Service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Emailtester {

    @Autowired
    private MailService mailService;

    @Test
    void testSendMail() {
        mailService.sendmail("sankeths908@gmail.com", "Test", "Testing mail");
    }
}
