package io.incondensable.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author abbas
 */
@Configuration
@PropertySource("classpath:mail.properties")
public class GmailConfiguration {

    @Value("${meniyu.mail.protocol}")
    private String protocol;

    @Value("${meniyu.mail.host}")
    private String host;

    @Value("${meniyu.mail.port}")
    private int port;

    @Value("${meniyu.mail.username}")
    public static String username;

    @Value("${meniyu.mail.password}")
    private String password;

    @Value("${meniyu.mail.smtp.starttls.enable}")
    private boolean startTls;

    @Value("${meniyu.mail.smtp.auth}")
    private boolean auth;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mail = new JavaMailSenderImpl();
        Properties props = new Properties();
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.startTls.enable", startTls);

        mail.setJavaMailProperties(props);
        mail.setUsername(username);
        mail.setPassword(password);
        mail.setProtocol(protocol);
        mail.setHost(host);
        mail.setPort(port);

        return mail;
    }

}
