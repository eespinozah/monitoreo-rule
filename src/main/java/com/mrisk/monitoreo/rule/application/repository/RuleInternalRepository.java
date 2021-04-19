package com.mrisk.monitoreo.rule.application.repository;

import com.mrisk.monitoreo.rule.domain.Rule;

public interface RuleInternalRepository {
	
    Rule save(Rule rule);

    Rule update(Rule rule);
}
