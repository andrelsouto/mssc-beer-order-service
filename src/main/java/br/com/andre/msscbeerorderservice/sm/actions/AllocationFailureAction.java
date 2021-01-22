package br.com.andre.msscbeerorderservice.sm.actions;

import br.com.andre.msscbeerorderservice.domain.BeerOrderEventEnum;
import br.com.andre.msscbeerorderservice.domain.BeerOrderStatusEnum;
import org.springframework.statemachine.action.Action;

public interface AllocationFailureAction extends Action<BeerOrderStatusEnum, BeerOrderEventEnum> {
}
