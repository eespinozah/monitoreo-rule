package com.mrisk.monitoreo.rule.domain;

import java.util.Calendar;

import lombok.Data;

@Data
public class SubComponent {

    private Integer csubId;
    private String name;
    private String description;

    private Boolean alive = Boolean.TRUE;
    private Calendar creationTime = Calendar.getInstance();
    private Calendar modificationTime;
    private Calendar destructionTime;
    
    public void setCsubId(Integer csubId) {
        if(csubId == 0) {
            this.csubId = null ; 
        }
        else {
            this.csubId = csubId; 
        }
    }

}
