package br.com.andre.msscbeerorderservice.config;

import br.com.andre.msscbeerorderservice.web.model.events.*;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public static final String ALLOCATE_FAILURE_QUEUE = "allocation-failure";
    public static final String DEALLOCATE_ORDER_QUEUE = "deallocate-order";

    @Bean
    public MessageConverter jacksonJmsConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("JMS_VALIDATE_REQUEST", ValidateOrderRequest.class);
        typeIdMappings.put("JMS_VALIDATE_RESPONSE", ValidateOrderResult.class);
        typeIdMappings.put("JMS_ALLOCATE_ORDER_REQUEST", AllocateOrderRequest.class);
        typeIdMappings.put("JMS_ALLOCATE_ORDER_RESPONSE", AllocateOrderResult.class);
        typeIdMappings.put("JMS_ALLOCATION_FAILURE_EVENT", AllocationFailureEvent.class);
        typeIdMappings.put("JMS_DEALLOCATE_FAILURE_EVENT", DeallocateOrderRequest.class);
        converter.setTypeIdPropertyName("_type");
        converter.setTypeIdMappings(typeIdMappings);
        converter.setObjectMapper(objectMapper);
        return converter;
    }

}
