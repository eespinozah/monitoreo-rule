package com.mrisk.monitoreo.rule.application.service;

import java.util.List;

import com.mrisk.monitoreo.rule.application.repository.ParameterUnitRepository;
import com.mrisk.monitoreo.rule.domain.ParameterUnit;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParameterUnitService {

	private final ParameterUnitRepository repository;

	public List<ParameterUnit> getListParameterUnit() {

		return repository.getListParameterUnit();
		
	}

}
