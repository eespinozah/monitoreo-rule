package com.mrisk.monitoreo.rule.application.repository;

import java.util.List;

import com.mrisk.monitoreo.rule.domain.Rule;
import com.mrisk.monitoreo.rule.domain.RuleFilter;

public interface RuleRepository {
	
	Rule findById(Integer tenaId, Integer accoId, Integer ruleId);

	Rule save(Rule rule);

    List<Rule> getRulesByFilters(RuleFilter filters);

}
