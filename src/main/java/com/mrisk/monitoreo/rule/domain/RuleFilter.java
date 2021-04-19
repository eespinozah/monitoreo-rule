package com.mrisk.monitoreo.rule.domain;

import lombok.Data;

@Data
public class RuleFilter {
    
    private Integer tenaId;
    private Integer accoId;
    private String  normName;
    private Integer normValue;
    private Integer typeId;
    private Integer compId;
    

}
