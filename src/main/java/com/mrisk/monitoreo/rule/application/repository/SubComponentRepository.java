package com.mrisk.monitoreo.rule.application.repository;

import java.util.List;

import com.mrisk.monitoreo.rule.domain.SubComponent;

public interface SubComponentRepository {

    List<SubComponent> findAllSubComponentByComponentId(Integer tenaId, Integer compId);

    SubComponent findSubCompByCompIdAndSubId( Integer tenaId,Integer id, Integer csubid );

}
