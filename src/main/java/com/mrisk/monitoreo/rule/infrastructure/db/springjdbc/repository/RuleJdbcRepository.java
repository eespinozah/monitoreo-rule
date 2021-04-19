package com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.mrisk.monitoreo.rule.application.repository.RuleRepository;
import com.mrisk.monitoreo.rule.domain.Rule;
import com.mrisk.monitoreo.rule.domain.RuleFilter;
import com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository.mapper.RuleMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RuleJdbcRepository implements RuleRepository {

    private static final String ORDER_BY = " order by  n.norm_id asc ";
    private static final String QUERY_BASE = "select n.norm_id, n.name, n.number,n.creation_time ,  n.date_publication, n.description, n.detail, c.comp_id, c.name as comp_name, cs.csub_id, cs.name as csub_name,nt.type_id, nt.name as rule_type_name, n.legal, n.tena_id, n.acco_id, n.organism_inspector_id, n.organism_issuing_id, n.norm_link from norm n";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StringBuilder queryBuilderBaseForRuleAll() {
        StringBuilder st = new StringBuilder();
        st.append(QUERY_BASE).append(" join component c on (n.comp_id  = c.comp_id ) ")
                .append(" left join component_sub cs on (n.csub_id = cs.csub_id) ")
                .append(" join norm_type nt  on (n.type_id = nt.type_id ) ").append(" where n.alive = true ")
                .append(" and n.tena_id = ? ").append(" and n.acco_id = ?");
        return st;
    }

    @Override
    public Rule findById(Integer tenaId, Integer accoId, Integer ruleId) {
        StringBuilder st = queryBuilderBaseForRuleAll();

        st.append(" and n.norm_id = ?").append(ORDER_BY);
        try {

            return jdbcTemplate.queryForObject(st.toString(), new RuleMapper(), tenaId, accoId, ruleId);

        } catch (EmptyResultDataAccessException noResult) {

            return null;
        }
    }

    @Override
    public Rule save(Rule rule) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName("norm")
                .usingGeneratedKeyColumns("norm_id");

        SqlParameterSource parameters = new BeanPropertySqlParameterSource(rule);
        Number newId = insert.executeAndReturnKey(parameters);
        rule.setNormId(newId.intValue());
        return rule;
    }

    @Override
    public List<Rule> getRulesByFilters(RuleFilter filter) {
        StringBuilder queryBuilder = queryBuilderBaseForRuleAll();

        if (Objects.nonNull(filter.getNormName())) {
            queryBuilder.append(" and n.name like '%").append(filter.getNormName()).append("%'");
        }
        if (Objects.nonNull(filter.getNormValue())) {
            queryBuilder.append(" and nt.value_id =").append(filter.getNormValue());
        }
        if (Objects.nonNull(filter.getTypeId())) {
            queryBuilder.append(" and n.type_id =").append(filter.getTypeId());
        }

        if (Objects.nonNull(filter.getCompId())) {
            queryBuilder.append(" and n.comp_id =").append(filter.getCompId());
        }
        queryBuilder.append(ORDER_BY);

        try {
            return jdbcTemplate.query(queryBuilder.toString(), new RuleMapper(), filter.getTenaId(),
                    filter.getAccoId());

        } catch (EmptyResultDataAccessException noResult) {

            return new ArrayList<>();
        }
    }

}
