package com.mrisk.monitoreo.rule.application.service;

import java.util.List;
import java.util.Objects;

import com.mrisk.monitoreo.rule.application.constants.RuleConstants;
import com.mrisk.monitoreo.rule.application.exception.DataNotFoundException;
import com.mrisk.monitoreo.rule.application.repository.SubComponentRepository;
import com.mrisk.monitoreo.rule.domain.SubComponent;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SubComponentService {

    private final SubComponentRepository repository;

    public List<SubComponent> findAllSubComponentByComponentId(Integer tenaId, Integer componentId) {
        List<SubComponent> subComponentList = repository.findAllSubComponentByComponentId(tenaId, componentId);
        if (subComponentList.isEmpty()) {
            throw new DataNotFoundException(RuleConstants.DATA_NOT_FOUND);
        }
        return subComponentList;
    }

    public SubComponent findSubCompByCompIdAndSubId(Integer tenaId, Integer id, Integer csubid) {
        SubComponent subComponent = repository.findSubCompByCompIdAndSubId(tenaId, id, csubid);
        if (Objects.nonNull(subComponent)) {
            return subComponent;
        }
        throw new DataNotFoundException(RuleConstants.DATA_NOT_FOUND);
    }

}
