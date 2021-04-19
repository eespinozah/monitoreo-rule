package com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mrisk.monitoreo.rule.domain.Component;

public class ComponentMapper implements RowMapper<Component> {

    @Override
    public Component mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Component component = new Component();
        component.setCompId(rs.getInt("comp_id"));
        component.setName(rs.getString("name"));
        return component;
    }
}
