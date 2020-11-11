package br.com.andre.msscbeerorderservice.domain;

public enum BeerOrderStatusEnum {
    NEW, VALIDATED, VALIDATION_PENDING, VALIDATION_EXCEPTION,
    ALLOCATION_PENDING, ALLOCATED, ALLOCATED_EXCEPTION,
    PENDING_INVENTORY, PICKED_UP, DELIVERED, DELIVERY_EXCEPTION
}