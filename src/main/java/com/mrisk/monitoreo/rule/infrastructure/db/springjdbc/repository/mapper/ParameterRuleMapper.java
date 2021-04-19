package com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mrisk.monitoreo.rule.domain.Parameter;
import com.mrisk.monitoreo.rule.domain.ParameterRule;

public class ParameterRuleMapper implements RowMapper<ParameterRule> {

    @Override
    public ParameterRule mapRow(ResultSet rs, int rowNum) throws SQLException {
        final ParameterRule parameterRule = new ParameterRule();
        Parameter parameter = new Parameter();
        parameter.setParaId(rs.getInt("para_id"));
        parameter.setName(rs.getString("para_name"));
        parameter.setSymbol(rs.getString("para_symbol"));
        parameter.setUnit(rs.getString("para_unit"));
        
        parameterRule.setParameter(parameter);
        parameterRule.setLimitMaximum(rs.getInt("limit_maximum"));
        parameterRule.setLimitMinimum(rs.getInt("limit_minimum"));
        parameterRule.setBreachOption(rs.getString("breach_option"));
        return parameterRule;
    }
}
