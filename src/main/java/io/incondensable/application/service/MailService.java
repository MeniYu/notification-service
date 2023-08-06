package io.incondensable.application.service;

import io.incondensable.application.config.GmailConfiguration;
import io.incondensable.application.domain.OtpGeneratedPayload;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

/**
 * @author abbas
 */
@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine thymeleaf;

    public MailService(JavaMailSender mailSender, SpringTemplateEngine thymeleaf) {
        this.mailSender = mailSender;
        this.thymeleaf = thymeleaf;
    }

    public void sendOtpCode(OtpGeneratedPayload otp) {
        try {
            Context ctx = new Context();
            ctx.setVariable("otpCode", otp.getOtpCode());
            String htmlContent = thymeleaf.process("/otp-mail-en.html", ctx);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setFrom(GmailConfiguration.username);
            helper.setTo(otp.getEmailAddress());
            helper.setSubject("One Time Password");
            helper.setText(htmlContent);

            message.saveChanges();
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
