package com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.mrisk.monitoreo.rule.application.repository.RuleInternalRepository;
import com.mrisk.monitoreo.rule.domain.Rule;
import com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository.mapsql.RuleInternalMapSql;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RuleInternalJdbcRepository implements RuleInternalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Rule save(Rule rule) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName("norm")
                .usingGeneratedKeyColumns("norm_id");

        SqlParameterSource parameters = new RuleInternalMapSql(rule);
        Number newId = insert.executeAndReturnKey(parameters);
        rule.setNormId(newId.intValue());
        return rule;
    }

    @Override
    public Rule update(Rule rule) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("update norm ").append("set alive = ?").append(", destruction_time = ? ")
                .append(" where norm_id = ? ").append(" and tena_id= ?").append(" and acco_id = ? ");
        jdbcTemplate.update(queryBuilder.toString(), rule.getAlive(), rule.getDestructionTime(), rule.getNormId(),
                rule.getTenaId(), rule.getAccoId());
        return rule;
    }

}
