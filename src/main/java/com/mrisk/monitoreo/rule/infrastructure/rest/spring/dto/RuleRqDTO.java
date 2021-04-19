package com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuleRqDTO {

    private Integer normId;
    private String name;
    private String detail;
    private RuleTypeDTO ruleType; 
    private ComponentDTO component;
    private Boolean legal;
    
    //interna
    private SubComponentDTO subComponent;
    private String description;

}
