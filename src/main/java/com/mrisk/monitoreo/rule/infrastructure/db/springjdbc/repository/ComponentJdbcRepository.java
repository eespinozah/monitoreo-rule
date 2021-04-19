package com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mrisk.monitoreo.rule.application.repository.ComponentRepository;
import com.mrisk.monitoreo.rule.domain.Component;
import com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository.mapper.ComponentMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ComponentJdbcRepository implements ComponentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Component findById(Integer tenaId, Integer compId) {
        StringBuilder st = new StringBuilder();
        st.append("SELECT c.comp_id , c.description, name  as name ").append(
                "FROM component c JOIN component_tenant ct on (c.comp_id =ct.comp_id ) where ct.tena_id  = ? and c.comp_id= ? and c.alive = true");
        try {

            return jdbcTemplate.queryForObject(st.toString(), new BeanPropertyRowMapper<Component>(Component.class),
                    tenaId, compId);

        } catch (EmptyResultDataAccessException noResult) {

            return null;
        }
    }

    @Override
    public List<Component> findComponents(Integer tenaId) {
        StringBuilder st = new StringBuilder();
        st.append("SELECT c.comp_id , c.description, name  as name ").append(
                "FROM component c JOIN component_tenant ct on (c.comp_id =ct.comp_id ) and ct.tena_id  = ? and c.alive = true");
        try {

            return jdbcTemplate.query(st.toString(), new ComponentMapper(), tenaId);

        } catch (EmptyResultDataAccessException noResult) {

            return new ArrayList<>();
        }
    }

}
