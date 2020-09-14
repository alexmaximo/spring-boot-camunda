package com.github.ricardocomar.springbootcamunda.appgateway.gateway.mapper;

import com.github.ricardocomar.springbootcamunda.appgateway.gateway.model.OrderRequest;
import com.github.ricardocomar.springbootcamunda.appgateway.model.CreditCard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CreditCardMapper {

public abstract CreditCard fromRequest(OrderRequest.CreditCard card);
    
}