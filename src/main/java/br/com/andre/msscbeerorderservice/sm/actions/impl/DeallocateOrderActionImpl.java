package br.com.andre.msscbeerorderservice.sm.actions.impl;

import br.com.andre.msscbeerorderservice.config.JmsConfig;
import br.com.andre.msscbeerorderservice.domain.BeerOrder;
import br.com.andre.msscbeerorderservice.domain.BeerOrderEventEnum;
import br.com.andre.msscbeerorderservice.domain.BeerOrderStatusEnum;
import br.com.andre.msscbeerorderservice.repositories.BeerOrderRepository;
import br.com.andre.msscbeerorderservice.services.BeerOrderManagerImpl;
import br.com.andre.msscbeerorderservice.sm.actions.DeallocateOrderAction;
import br.com.andre.msscbeerorderservice.web.mappers.BeerOrderMapper;
import br.com.andre.msscbeerorderservice.web.model.events.DeallocateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeallocateOrderActionImpl implements DeallocateOrderAction {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {

        String beerOrderId = (String) stateContext.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        Optional<BeerOrder> beerOrderOptional = beerOrderRepository.findById(UUID.fromString(beerOrderId));

        beerOrderOptional.ifPresentOrElse(beerOrder -> {

            jmsTemplate.convertAndSend(JmsConfig.DEALLOCATE_ORDER_QUEUE,
                    DeallocateOrderRequest.builder().beerOrderDto(beerOrderMapper.beerOrderToDto(beerOrder)).build());

            log.debug("Sent Allocation Request for order id: " + beerOrderId);
        }, () -> log.error("Beer Order Not Found Id: " + stateContext.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER)));

    }
}
