package br.com.andre.msscbeerorderservice.services.listeners;

import br.com.andre.msscbeerorderservice.config.JmsConfig;
import br.com.andre.msscbeerorderservice.services.BeerOrderManager;
import br.com.andre.msscbeerorderservice.web.model.events.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderAllocationResultListener {

    private final BeerOrderManager beerOrderManager;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE)
    public void listen(AllocateOrderResult allocateOrderResult) {
        if (!allocateOrderResult.getAllocationError() && !allocateOrderResult.getPendingInventory()) {

            beerOrderManager.beerOrderAllocationPassed(allocateOrderResult.getBeerOrderDto());
        } else if (!allocateOrderResult.getAllocationError() && allocateOrderResult.getPendingInventory()) {

            beerOrderManager.beerOrderAllocationPendingInventory(allocateOrderResult.getBeerOrderDto());
        } else if (allocateOrderResult.getAllocationError()) {

            beerOrderManager.beerOrderAllocationFailed(allocateOrderResult.getBeerOrderDto());
        }
    }

}
