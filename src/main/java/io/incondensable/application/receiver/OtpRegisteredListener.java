package io.incondensable.application.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.incondensable.application.ObjectConverter;
import io.incondensable.application.domain.OtpGeneratedPayload;
import io.incondensable.application.service.MailService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author abbas
 * With any messaging-based application, you need to create a receiver that responds to published messages.
 * The Receiver is a POJO that defines a method for receiving messages. When you register it to receive messages,
 * you can name it anything you want.
 */
@Component
@EnableRabbit
public class OtpRegisteredListener {

    @Autowired
    private MailService mailService;
    @Autowired
    private ObjectConverter<OtpGeneratedPayload> converter;

    public OtpRegisteredListener(MailService mailService) {
        this.mailService = mailService;
    }

    @RabbitListener(queues = {"owner_fanout_queue"})
    public void receiveOtp(String payload) throws JsonProcessingException {
        mailService.sendOtpCode(
                converter.convert(payload)
        );
    }

}
