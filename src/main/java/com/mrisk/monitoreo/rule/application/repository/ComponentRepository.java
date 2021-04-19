package com.mrisk.monitoreo.rule.application.repository;

import java.util.List;

import com.mrisk.monitoreo.rule.domain.Component;

public interface ComponentRepository {

    Component findById( Integer tenaId, Integer id);

    List<Component> findComponents( Integer tenaId);

}
