package com.vikram.companyservice.messaging;

import com.vikram.companyservice.dto.CompanyMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyMessageProducer {
    private static final Logger logger = LoggerFactory.getLogger(CompanyMessageProducer.class);
    private final RabbitTemplate rabbitTemplate;
    public void sendMessage(Long companyId) {
        CompanyMessage companyMessage = new CompanyMessage();
        companyMessage.setCompanyId(companyId);
        // Default exchange used
        // In which routing key=queue name
        rabbitTemplate.convertAndSend("x.delete-company","",companyMessage);
        logger.info("Message sent with companyId: {}",companyId);
    }
}
