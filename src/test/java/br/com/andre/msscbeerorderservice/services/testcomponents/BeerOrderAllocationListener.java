package br.com.andre.msscbeerorderservice.services.testcomponents;

import br.com.andre.msscbeerorderservice.config.JmsConfig;
import br.com.andre.msscbeerorderservice.web.model.events.AllocateOrderRequest;
import br.com.andre.msscbeerorderservice.web.model.events.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderAllocationListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void  listen(Message message) {

        AllocateOrderRequest orderRequest = (AllocateOrderRequest) message.getPayload();

        orderRequest.getBeerOrderDto().getBeerOrderLines()
                .forEach(beerOrderLineDto -> beerOrderLineDto.setQuantityAllocated(beerOrderLineDto.getOrderQuantity()));

        AllocateOrderResult orderResult = AllocateOrderResult.builder()
                .beerOrderDto(orderRequest.getBeerOrderDto())
                .allocationError(false)
                .pendingInventory(false)
                .build();

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, orderResult);

    }

}
