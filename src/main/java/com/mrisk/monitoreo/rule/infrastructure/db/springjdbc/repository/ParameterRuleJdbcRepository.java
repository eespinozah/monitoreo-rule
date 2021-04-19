package com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrisk.monitoreo.rule.application.repository.ParameterRuleRepository;
import com.mrisk.monitoreo.rule.domain.ParameterRule;
import com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository.mapper.ParameterRuleMapper;
import com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository.mapsql.ParameterRuleMapSql;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ParameterRuleJdbcRepository implements ParameterRuleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveParameterRule(ParameterRule parameterRule) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName("norm_parameter");

        SqlParameterSource parameters = new ParameterRuleMapSql(parameterRule);
        insert.execute(parameters);

    }

    @Override
    public List<ParameterRule> findParametersRuleByRuleId(Integer tenaId, Integer ruleId) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(
                "select p.para_id, p.name para_name, pu.name para_unit,p.symbol para_symbol,np.limit_minimum, np.limit_maximum, np.breach_option from norm_parameter np")
                .append(" join \"parameter\" p ON  (np.para_id = p.para_id) ")
                .append(" join norm n on (n.norm_id = np.norm_id) ")
                .append(" join parameter_unit pu on (p.unit_id = pu.unit_id)").append("where n.norm_id  = ? ")
                .append(" and np.tena_id = ? ")
                .append(" and np.tena_id = n.tena_id ");
        try {
            return jdbcTemplate.query(queryBuilder.toString(), new ParameterRuleMapper(), ruleId, tenaId);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
