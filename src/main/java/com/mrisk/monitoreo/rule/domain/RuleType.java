package com.mrisk.monitoreo.rule.domain;

import lombok.Data;

@Data
public class RuleType {
    
    private Integer typeId;
    private String name;
    private Boolean alive;
    private String description;
    private Integer valueId; 

}
