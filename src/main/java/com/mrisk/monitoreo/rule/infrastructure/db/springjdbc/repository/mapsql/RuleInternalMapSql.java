package com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository.mapsql;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.mrisk.monitoreo.rule.domain.Rule;

public class RuleInternalMapSql extends MapSqlParameterSource {

    public RuleInternalMapSql(Rule rule) {
        super();
        this.addValue("norm_id", rule.getNormId());
        this.addValue("name", rule.getName());
        this.addValue("detail", rule.getDetail());
        this.addValue("type_id", rule.getRuleType().getTypeId());
        this.addValue("comp_id", rule.getComponent().getCompId());
        this.addValue("csub_id", rule.getSubComponent().getCsubId());
        this.addValue("description", rule.getDescription());
        this.addValue("creation_time", rule.getCreationTime());
        this.addValue("legal", rule.getLegal());
        this.addValue("alive", rule.getAlive());
        this.addValue("tena_id", rule.getTenaId());
        this.addValue("acco_id", rule.getAccoId());
        this.addValue("destruction_time", rule.getDestructionTime());
        this.addValue("modification_time", rule.getDestructionTime());
    }

}
