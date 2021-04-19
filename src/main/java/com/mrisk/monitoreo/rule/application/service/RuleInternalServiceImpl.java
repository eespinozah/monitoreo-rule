package com.mrisk.monitoreo.rule.application.service;

import java.time.LocalDateTime;

import com.mrisk.monitoreo.rule.application.exception.BusinessException;
import com.mrisk.monitoreo.rule.application.repository.RuleInternalRepository;
import com.mrisk.monitoreo.rule.domain.Rule;
import com.mrisk.monitoreo.rule.domain.RuleType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RuleInternalServiceImpl {

    private static final int INTERN_RULE_TYPE = 1;

    private final RuleInternalRepository repository;

    public Rule saveRule(Rule rule) {

        if (rule.getLegal().booleanValue()) {
            throw new BusinessException("no se pueden crear normas legales");
        }

        RuleType ruleType = new RuleType();
        ruleType.setTypeId(INTERN_RULE_TYPE);
        rule.setRuleType(ruleType);
        return repository.save(rule);

    }


    public Rule deleteRule(Rule rule) {
        
        rule.setAlive(Boolean.FALSE);
        rule.setDestructionTime(LocalDateTime.now());
        
        return  repository.update(rule);
    }

}
