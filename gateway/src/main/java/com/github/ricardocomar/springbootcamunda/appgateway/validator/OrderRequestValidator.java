package com.github.ricardocomar.springbootcamunda.appgateway.validator;

import static br.com.fluentvalidator.predicate.ComparablePredicate.greaterThan;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import com.github.ricardocomar.springbootcamunda.appgateway.entrypoint.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.fluentvalidator.AbstractValidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestValidator extends AbstractValidator<OrderRequest> {

    @Autowired
    private CreditCardValidator ccValidator;

    @Autowired
    private BankSlipValidator bsValidator;

    @Override
    public void rules() {

        ruleFor("order.name", OrderRequest::getCustomer).must(not(stringEmptyOrNull()))
                .withMessage("Order customer is mandatory").critical();

        ruleFor("order.value", OrderRequest::getValue).must(greaterThan(0.0))
                .withMessage("Order value is mandatory and must be positive").critical();

        ruleFor("order.card", o -> o).must(
                (nullValue(OrderRequest::getCard).and(not(nullValue(OrderRequest::getBankSlip))))
                        .or(not(nullValue(OrderRequest::getCard))
                                .and(nullValue(OrderRequest::getBankSlip))))
                .withMessage(
                        "Order Card or Bank Slip is mandatory at least one of them, but not both")
                .critical();

        ruleFor(OrderRequest::getCard).whenever(not(nullValue())).withValidator(ccValidator)
                .critical();
        ruleFor(OrderRequest::getBankSlip).whenever(not(nullValue())).withValidator(bsValidator)
                .critical();

    }
}
