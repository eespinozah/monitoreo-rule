package com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mrisk.monitoreo.rule.application.repository.ParameterRepository;
import com.mrisk.monitoreo.rule.domain.Parameter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ParameterJdbcRepository implements ParameterRepository {

    private static final String BASE_SELECT = " as name FROM parameter p join parameter_unit pu on (p.unit_id = pu.unit_id) join parameter_tenant pt on (p.para_id = pt.para_id) where 1 = 1";
    private static final String SELECT_BASE_PARAMETER = "SELECT  p.para_id, p.csub_id, symbol,pu.name unit, discrete, p.name ";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Parameter singleSelectParameter(Integer tenaId,Integer paraId) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_BASE_PARAMETER).append(BASE_SELECT)
        .append(" and p.para_id= ? ")
        .append(" and pt.tena_id= ? ")
        .append(" and p.alive = true ");
       
        try {

            return jdbcTemplate.queryForObject(query.toString(), new BeanPropertyRowMapper<Parameter>(Parameter.class), paraId, tenaId);

        } catch (EmptyResultDataAccessException noResult) {

            return null;
        }

    }

    @Override
    public List<Parameter> findParametersByRequestsFilter(Integer tenaId,Integer compId, Integer csubId, String parameterName) {

        try {

            String sql = createQueryForFilter(compId, csubId, parameterName);
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Parameter>(Parameter.class), tenaId);

        } catch (EmptyResultDataAccessException noResult) {

            return new ArrayList<>();
        }
    }

    private String createQueryForFilter( Integer compId, Integer csubId, String parameterName) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_BASE_PARAMETER).append(BASE_SELECT)
        .append(" and pt.tena_id= ? ")
        .append(" and p.alive = true ");
        if (Objects.nonNull(compId)) {
            query.append(" and p.csub_id in (select csub_id from component_sub cs where comp_id  =" + compId + ")");
        }

        if (Objects.nonNull(csubId)) {
            query.append(" and csub_id =" + csubId + "");
        }

        if (Objects.nonNull(parameterName)) {
            query.append(" and p.name = '" + parameterName + "'");
        }
        query.append(" order by p.para_id asc ");
        return query.toString();
    }

}
