package br.com.andre.msscbeerorderservice.sm.actions.impl;

import br.com.andre.msscbeerorderservice.domain.BeerOrderEventEnum;
import br.com.andre.msscbeerorderservice.domain.BeerOrderStatusEnum;
import br.com.andre.msscbeerorderservice.services.BeerOrderManagerImpl;
import br.com.andre.msscbeerorderservice.sm.actions.ValidationFailureAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidationFailureActionImpl implements ValidationFailureAction {
    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {
        String beerOderId = (String) stateContext.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        log.error("Compensating Transaction...  Validtion failed: " + beerOderId);
    }
}
