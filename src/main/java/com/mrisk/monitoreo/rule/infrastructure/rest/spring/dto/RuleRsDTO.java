package com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuleRsDTO  {

    private Integer normId;
    private String name;
    private String detail;
    private RuleTypeDTO ruleType; 
    private ComponentDTO component;
    private Boolean legal;
    
    //interna
    private SubComponentDTO subComponent;
    private String description;
    private LocalDateTime creationTime;
    
    //legal 
    private String number;
    private LocalDate datePublication;
    private Integer organismIssuingId;
    private Integer organismInspectorId;
    private String normLink; 
 


}
