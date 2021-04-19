package com.mrisk.monitoreo.rule.application.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mrisk.monitoreo.rule.application.constants.RuleConstants;
import com.mrisk.monitoreo.rule.application.exception.BusinessException;
import com.mrisk.monitoreo.rule.application.exception.DataNotFoundException;
import com.mrisk.monitoreo.rule.application.repository.ParameterRuleRepository;
import com.mrisk.monitoreo.rule.domain.ParameterRule;
import com.mrisk.monitoreo.rule.domain.Rule;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParameterRuleService {

    private final ParameterRuleRepository repository;

    public void saveParameterRule(Integer tenaId, Integer ruleId, List<ParameterRule> listParameterRule) {

        validateParameters(listParameterRule);

        listParameterRule.forEach(parRule -> {
            Rule rule = new Rule(ruleId);
            parRule.setRule(rule);
            parRule.setTenaId(tenaId);
            repository.saveParameterRule(parRule);
        });
    }

    private void validateParameters(List<ParameterRule> listParameterRule) {
        List<Integer> duplicateList = listParameterRule.stream()
                .collect(Collectors.groupingBy(s -> s.getParameter().getParaId())).entrySet().stream()
                .filter(e -> e.getValue().size() > 1).map(e -> e.getKey()).collect(Collectors.toList());
        if (!duplicateList.isEmpty()) {
            throw new BusinessException("Lista contiene parametros repetidos");
        }
    }

    public List<ParameterRule> findParametersRuleByRuleId(Integer tenaId, Integer ruleId) {
        List<ParameterRule> listReturn = repository.findParametersRuleByRuleId(tenaId, ruleId);
        if (listReturn.isEmpty()) {
            throw new DataNotFoundException(RuleConstants.DATA_NOT_FOUND);
        }
        return listReturn;
    }

}
