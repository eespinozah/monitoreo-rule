package com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mrisk.monitoreo.rule.application.repository.ParameterDiscreteRepository;
import com.mrisk.monitoreo.rule.domain.ParameterDiscrete;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ParameterDiscreteJdbcRepository implements ParameterDiscreteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ParameterDiscrete> findParametersDiscreteByParaId(Integer tenaId, Integer paraId) {
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("select disc_id, name, out_norm  from parameter_discrete pd ")
                    .append(" join parameter_tenant pt on (pt.para_id = pd.para_id)").append(" where pd.para_id = ? ")
                    .append(" and pt.tena_id = ? ").append(" and pd.alive = true ");
            return jdbcTemplate.query(queryBuilder.toString(),
                    new BeanPropertyRowMapper<ParameterDiscrete>(ParameterDiscrete.class), paraId, tenaId);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

}
