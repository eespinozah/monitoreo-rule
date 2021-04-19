package com.mrisk.monitoreo.rule.domain;


import java.util.Calendar;

import lombok.Data;

@Data
public class Component {

    private Integer compId;
    private String name;
    private String description;

    private Boolean alive = Boolean.TRUE;
    private Calendar creationTime = Calendar.getInstance();
    private Calendar modificationTime;
    private Calendar destructionTime;

}
