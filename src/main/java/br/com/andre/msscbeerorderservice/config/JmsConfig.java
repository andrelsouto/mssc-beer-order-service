package br.com.andre.msscbeerorderservice.config;

import br.com.andre.msscbeerorderservice.web.model.events.AllocateOrderRequest;
import br.com.andre.msscbeerorderservice.web.model.events.AllocateOrderResult;
import br.com.andre.msscbeerorderservice.web.model.events.ValidateOrderRequest;
import br.com.andre.msscbeerorderservice.web.model.events.ValidateOrderResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmsConfig {

    public static final String VALIDATE_ORDER_QUEUE = "validate-order";
    public static final String VALIDATE_ORDER_RESPONSE_QUEUE = "validate-order-response";
    public static final String ALLOCATE_ORDER_QUEUE = "allocate-order";
    public static final String ALLOCATE_ORDER_RESPONSE_QUEUE = "allocate-order-response";

    @Bean
    public MessageConverter jacksonJmsConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("JMS_VALIDATE_REQUEST", ValidateOrderRequest.class);
        typeIdMappings.put("JMS_VALIDATE_RESPONSE", ValidateOrderResult.class);
        typeIdMappings.put("JMS_ALLOCATE_ORDER_REQUEST", AllocateOrderRequest.class);
        typeIdMappings.put("JMS_ALLOCATE_ORDER_RESPONSE", AllocateOrderResult.class);
        converter.setTypeIdPropertyName("_type");
        converter.setTypeIdMappings(typeIdMappings);
        return converter;
    }

}
