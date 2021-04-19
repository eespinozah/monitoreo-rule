package com.mrisk.monitoreo.rule.application.repository;

import java.util.List;

import com.mrisk.monitoreo.rule.domain.ParameterRule;

public interface ParameterRuleRepository {

    void saveParameterRule(ParameterRule parameterRule);

    List<ParameterRule> findParametersRuleByRuleId(Integer tenaId,Integer ruleId);

}
