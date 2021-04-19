package com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mrisk.monitoreo.rule.application.repository.SubComponentRepository;
import com.mrisk.monitoreo.rule.domain.SubComponent;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubComponentJdbcRepository implements SubComponentRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<SubComponent> findAllSubComponentByComponentId( Integer tenaId, Integer compId) {
        StringBuilder query = new StringBuilder();
        query.append("select cs.csub_id, name  as name, cs.description from component_sub cs join component_sub_tenant cst on (cs.csub_id=cst.csub_id) where cst.tena_id = ?  and cs.comp_id = ? and cs.alive = true");
        try {

            return jdbcTemplate.query(query.toString(), new BeanPropertyRowMapper<SubComponent>(SubComponent.class),
                    tenaId, compId);

        } catch (EmptyResultDataAccessException noResult) {

            return new ArrayList<>();
        }
    }

    @Override
    public SubComponent findSubCompByCompIdAndSubId( Integer tenaId, Integer compId, Integer csubid) {
        StringBuilder query = new StringBuilder();
        query.append("select cs.csub_id, name  as name, cs.description from component_sub cs join component_sub_tenant cst on (cs.csub_id=cst.csub_id) where")
        .append(" cst.tena_id = ? ")
        .append(" and cs.comp_id = ?")
        .append(" and cs.csub_id = ? ")
        .append(" and cs.alive = true");
        
        try {

            return jdbcTemplate.queryForObject(query.toString(),
                    new BeanPropertyRowMapper<SubComponent>(SubComponent.class),tenaId, compId, csubid);

        } catch (EmptyResultDataAccessException noResult) {

            return null;
        }
    }

}
