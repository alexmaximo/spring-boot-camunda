package com.github.ricardocomar.springbootcamunda.mockservice.usecase;

import com.github.ricardocomar.springbootcamunda.mockservice.gateway.RemoveScenarioGateway;
import com.github.ricardocomar.springbootcamunda.mockservice.gateway.SaveScenarioGateway;
import com.github.ricardocomar.springbootcamunda.mockservice.model.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveScenarioUseCase {

    @Autowired
    private RemoveScenarioGateway removeGateway;

    @Autowired
    private SaveScenarioGateway saveGateway;

    public void delete(Long id) {
        removeGateway.remove(id);
    }

    public Scenario save(Scenario scenario) {
        return saveGateway.save(scenario);
    }

}