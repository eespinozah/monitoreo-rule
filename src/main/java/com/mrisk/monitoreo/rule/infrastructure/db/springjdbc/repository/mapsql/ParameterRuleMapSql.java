package com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository.mapsql;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.mrisk.monitoreo.rule.domain.ParameterRule;

public class ParameterRuleMapSql extends MapSqlParameterSource {

    public ParameterRuleMapSql(ParameterRule parameterRule) {
        super();
        this.addValue("para_id", parameterRule.getParameter().getParaId());
        this.addValue("norm_id", parameterRule.getRule().getNormId());
        this.addValue("limit_minimum", parameterRule.getLimitMinimum());
        this.addValue("limit_maximum", parameterRule.getLimitMaximum());
        this.addValue("breach_option", parameterRule.getBreachOption());
        this.addValue("tena_id", parameterRule.getTenaId());
    }

}
