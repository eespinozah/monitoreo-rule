package com.mrisk.monitoreo.rule.infrastructure.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mrisk.monitoreo.rule.application.repository.ComponentRepository;
import com.mrisk.monitoreo.rule.application.repository.ParameterDiscreteRepository;
import com.mrisk.monitoreo.rule.application.repository.ParameterRepository;
import com.mrisk.monitoreo.rule.application.repository.ParameterRuleRepository;
import com.mrisk.monitoreo.rule.application.repository.ParameterUnitRepository;
import com.mrisk.monitoreo.rule.application.repository.RuleInternalRepository;
import com.mrisk.monitoreo.rule.application.repository.RuleRepository;
import com.mrisk.monitoreo.rule.application.repository.SubComponentRepository;
import com.mrisk.monitoreo.rule.application.service.ComponentService;
import com.mrisk.monitoreo.rule.application.service.ParameterDiscreteService;
import com.mrisk.monitoreo.rule.application.service.ParameterRuleService;
import com.mrisk.monitoreo.rule.application.service.ParameterService;
import com.mrisk.monitoreo.rule.application.service.ParameterUnitService;
import com.mrisk.monitoreo.rule.application.service.RuleInternalServiceImpl;
import com.mrisk.monitoreo.rule.application.service.RuleReportService;
import com.mrisk.monitoreo.rule.application.service.RuleService;
import com.mrisk.monitoreo.rule.application.service.SubComponentService;

@Configuration
public class SpringBootServiceConfig {

    @Bean
    public RuleService ruleService(RuleRepository ruleRepository, RuleInternalServiceImpl ruleInternalService, RuleReportService ruleReportService) {
        return new RuleService(ruleRepository, ruleInternalService, ruleReportService);
    }

    @Bean
    public ParameterService parameterService(ParameterRepository parameterRepository) {
        return new ParameterService(parameterRepository);
    }

    @Bean
    public ParameterUnitService parameterUnitService(ParameterUnitRepository parameterUnitRepository) {
        return new ParameterUnitService(parameterUnitRepository);
    }

    @Bean
    public ComponentService componentService(ComponentRepository componentRepository) {
        return new ComponentService(componentRepository);
    }

    @Bean
    public SubComponentService subComponentService(SubComponentRepository subComponentRepository) {
        return new SubComponentService(subComponentRepository);
    }

    @Bean
    public ParameterRuleService parameterRuleService(ParameterRuleRepository parameterRuleRepository) {
        return new ParameterRuleService(parameterRuleRepository);
    }

    @Bean
    public ParameterDiscreteService parameterDiscreteService(ParameterDiscreteRepository parameterDiscreteRepository) {
        return new ParameterDiscreteService(parameterDiscreteRepository);
    }

    @Bean
    public RuleInternalServiceImpl ruleInternalServiceImpl(RuleInternalRepository ruleInternalRepository) {
        return new RuleInternalServiceImpl(ruleInternalRepository);
    }
    
    

}