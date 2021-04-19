package com.mrisk.monitoreo.rule.application.repository;

import java.util.List;

import com.mrisk.monitoreo.rule.domain.Parameter;

public interface ParameterRepository  {

	Parameter singleSelectParameter(Integer tenaId,Integer id);

    List<Parameter> findParametersByRequestsFilter(Integer tenaId,  Integer compId, Integer csubId, String parameterName);

}
