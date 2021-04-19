package com.mrisk.monitoreo.rule.application.repository;

import java.util.List;

import com.mrisk.monitoreo.rule.domain.ParameterDiscrete;

public interface ParameterDiscreteRepository {


    List<ParameterDiscrete> findParametersDiscreteByParaId(Integer tenaId, Integer paraID);

}
