package com.mrisk.monitoreo.rule.domain;


import java.util.Calendar;

import lombok.Data;

@Data
public class ParameterUnit {

    private Integer unitId;
    private String name;
    private String description;

    private Integer tenaId;
    private Boolean alive = Boolean.TRUE;
    private Calendar creationTime = Calendar.getInstance();
    private Calendar modificationTime;
    private Calendar destructionTime;
}
