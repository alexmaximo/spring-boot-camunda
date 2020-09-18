package com.github.ricardocomar.springbootcamunda.mockservice.gateway.entity;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ScenarioEntityFixture implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(ScenarioEntity.class).addTemplate("valid", new Rule() {
            {
                add("id", 1L);
                add("topicName", "mockTopic");
                add("scenarioId", "mockScenario");
                add("variables", has(3).of(VariableEntity.class, "boolean", "string", "long"));
            }
        });

    }

}