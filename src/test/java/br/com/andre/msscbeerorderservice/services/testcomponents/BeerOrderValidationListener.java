package br.com.andre.msscbeerorderservice.services.testcomponents;

import br.com.andre.msscbeerorderservice.config.JmsConfig;
import br.com.andre.msscbeerorderservice.web.model.events.ValidateOrderRequest;
import br.com.andre.msscbeerorderservice.web.model.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(Message msg) {

        ValidateOrderRequest request = (ValidateOrderRequest) msg.getPayload();

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult.builder()
                .isValid(true)
                .beerOrderId(request.getBeerOrder().getId())
                .build());

    }

}
