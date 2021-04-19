package com.mrisk.monitoreo.rule.infrastructure.db.springjdbc.repository.mapper;



import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mrisk.monitoreo.rule.domain.ParameterUnit;

public class ParameterUnitMapper implements RowMapper<ParameterUnit> {

	@Override
	public ParameterUnit mapRow(ResultSet rs, int rowNum) throws SQLException {

		final ParameterUnit parameterUnit = new ParameterUnit();
		parameterUnit.setUnitId(rs.getInt("unit_id"));
		parameterUnit.setName(rs.getString("name"));
		parameterUnit.setDescription(rs.getString("description"));
		return parameterUnit;
	}
}
