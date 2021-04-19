package com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.mrisk.monitoreo.rule.domain.Component;
import com.mrisk.monitoreo.rule.domain.Rule;
import com.mrisk.monitoreo.rule.domain.RuleType;
import com.mrisk.monitoreo.rule.domain.SubComponent;

public class RuleMapper implements RowMapper<Rule> {

    @Override
    public Rule mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Rule rule = new Rule();

        rule.setNormId(rs.getInt("norm_id"));
        rule.setName(rs.getString("name"));
        rule.setNumber(rs.getString("number"));
        rule.setCreationTime(rs.getObject("creation_time",LocalDateTime.class));
        rule.setDatePublication(rs.getObject("date_publication",LocalDate.class));
        rule.setDescription(rs.getString("description"));
        rule.setDetail(rs.getString("detail"));
        rule.setLegal(rs.getBoolean("legal"));
        rule.setTenaId(rs.getInt("tena_id"));
        rule.setAccoId(rs.getInt("acco_id"));
        rule.setOrganismInspectorId(rs.getInt("organism_inspector_id"));
        rule.setOrganismIssuingId(rs.getInt("organism_issuing_id"));
        rule.setNormLink(rs.getString("norm_link"));

        Component comp = new Component();
        comp.setCompId(rs.getInt("comp_id"));
        comp.setName(rs.getString("comp_name"));
        rule.setComponent(comp);

        SubComponent subComp = new SubComponent();
        subComp.setCsubId(rs.getInt("csub_id"));
        subComp.setName(rs.getString("csub_name"));

        RuleType ruleType = new RuleType();
        ruleType.setTypeId(rs.getInt("type_id"));
        ruleType.setName(rs.getString("rule_type_name"));

        rule.setRuleType(ruleType);

        rule.setSubComponent(subComp);

        return rule;
    }
}
