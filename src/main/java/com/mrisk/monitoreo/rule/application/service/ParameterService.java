package com.mrisk.monitoreo.rule.application.service;

import java.util.List;
import java.util.Objects;

import com.mrisk.monitoreo.rule.application.constants.RuleConstants;
import com.mrisk.monitoreo.rule.application.exception.DataNotFoundException;
import com.mrisk.monitoreo.rule.application.repository.ParameterRepository;
import com.mrisk.monitoreo.rule.domain.Parameter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParameterService {

    private final ParameterRepository repository;

    public Parameter singleSelectParameter(Integer tenaId, Integer id) {
        Parameter objParameter = repository.singleSelectParameter(tenaId, id);
        if (Objects.nonNull(objParameter)) {
            return objParameter;
        }
        throw new DataNotFoundException(RuleConstants.DATA_NOT_FOUND);
    }

    public List<Parameter> findParametersByRequestsFilter(Integer tenaId, Integer compId, Integer csubId,
            String parameterName) {
        List<Parameter> listParameters = repository.findParametersByRequestsFilter(tenaId, compId, csubId,
                parameterName);
        if (listParameters.isEmpty()) {
            throw new DataNotFoundException(RuleConstants.DATA_NOT_FOUND);
        }
        return listParameters;
    }

}
