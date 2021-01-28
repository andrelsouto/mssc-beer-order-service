package br.com.andre.msscbeerorderservice.services.testcomponents;

import br.com.andre.msscbeerorderservice.config.JmsConfig;
import br.com.andre.msscbeerorderservice.web.model.events.AllocateOrderRequest;
import br.com.andre.msscbeerorderservice.web.model.events.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderAllocationListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(Message message) {

        AllocateOrderRequest orderRequest = (AllocateOrderRequest) message.getPayload();
        AtomicBoolean pendingInventory = new AtomicBoolean(false);
        AtomicBoolean allocationError = new AtomicBoolean(false);
        boolean sendResponse = true;

        if (orderRequest.getBeerOrderDto().getCustomerRef() != null && orderRequest.getBeerOrderDto().getCustomerRef().equals("fail-allocation")) {
            allocationError.set(true);
        } else if (orderRequest.getBeerOrderDto().getCustomerRef() != null && orderRequest.getBeerOrderDto().getCustomerRef().equals("dont-allocate")) {
            sendResponse = false;
        }

        if (orderRequest.getBeerOrderDto().getCustomerRef() != null && orderRequest.getBeerOrderDto().getCustomerRef().equals("partial-allocation")) {
            pendingInventory.set(true);
        }

        orderRequest.getBeerOrderDto().getBeerOrderLines()
                .forEach(beerOrderLineDto -> {
                    if (pendingInventory.get()) {
                        beerOrderLineDto.setQuantityAllocated(beerOrderLineDto.getOrderQuantity() - 1);
                    } else {
                        beerOrderLineDto.setQuantityAllocated(beerOrderLineDto.getOrderQuantity());
                    }
                });

        AllocateOrderResult orderResult = AllocateOrderResult.builder()
                .beerOrderDto(orderRequest.getBeerOrderDto())
                .allocationError(allocationError.get())
                .pendingInventory(pendingInventory.get())
                .build();

        if (sendResponse) {
            jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, orderResult);
        }

    }

}
