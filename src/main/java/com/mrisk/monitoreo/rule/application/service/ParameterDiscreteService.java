package com.mrisk.monitoreo.rule.application.service;

import java.util.List;

import com.mrisk.monitoreo.rule.application.constants.RuleConstants;
import com.mrisk.monitoreo.rule.application.exception.DataNotFoundException;
import com.mrisk.monitoreo.rule.application.repository.ParameterDiscreteRepository;
import com.mrisk.monitoreo.rule.domain.ParameterDiscrete;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParameterDiscreteService {

    private final ParameterDiscreteRepository repository;

    public List<ParameterDiscrete> findParametersRuleByRuleId(Integer tenaId, Integer paraID) {
        List<ParameterDiscrete> listReturn = repository.findParametersDiscreteByParaId(tenaId,paraID);
        if (listReturn.isEmpty()) {
            throw new DataNotFoundException(RuleConstants.DATA_NOT_FOUND);
        }
        return listReturn;
    }

}
