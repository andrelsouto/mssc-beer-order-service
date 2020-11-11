package br.com.andre.msscbeerorderservice.sm.actions.impl;

import br.com.andre.msscbeerorderservice.config.JmsConfig;
import br.com.andre.msscbeerorderservice.domain.BeerOrder;
import br.com.andre.msscbeerorderservice.domain.BeerOrderEventEnum;
import br.com.andre.msscbeerorderservice.domain.BeerOrderStatusEnum;
import br.com.andre.msscbeerorderservice.repositories.BeerOrderRepository;
import br.com.andre.msscbeerorderservice.services.BeerOrderManagerImpl;
import br.com.andre.msscbeerorderservice.sm.actions.AllocationOrderAction;
import br.com.andre.msscbeerorderservice.web.mappers.BeerOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationOrderActionImpl implements AllocationOrderAction {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {

        String beerOrderId = (String) stateContext.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        BeerOrder beerOrder = beerOrderRepository.findOneById(UUID.fromString(beerOrderId));

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_QUEUE, beerOrderMapper.beerOrderToDto(beerOrder));

        log.debug("Sent Allocation Request for order id: " + beerOrderId);
    }
}
