package com.mrisk.monitoreo.rule.application.service;

import java.util.List;
import java.util.Objects;

import com.mrisk.monitoreo.rule.application.constants.RuleConstants;
import com.mrisk.monitoreo.rule.application.exception.DataNotFoundException;
import com.mrisk.monitoreo.rule.application.repository.ComponentRepository;
import com.mrisk.monitoreo.rule.domain.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ComponentService {

    private final ComponentRepository repository;

    public Component findComponentById(Integer tenaId, Integer id) {
        Component objComponent = repository.findById(tenaId, id);
        if (Objects.nonNull(objComponent)) {
            return objComponent;
        }
        throw new DataNotFoundException(RuleConstants.DATA_NOT_FOUND);
    }

    public List<Component> findComponents(Integer tenaId) {

        List<Component> listComponent = repository.findComponents(tenaId);
        if (listComponent.isEmpty()) {
            throw new DataNotFoundException(RuleConstants.DATA_NOT_FOUND);
        }

        return listComponent;
    }
}
