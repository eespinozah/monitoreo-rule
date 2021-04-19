package com.mrisk.monitoreo.rule.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rule {

    private Integer normId;
    private String name;
    private String detail;
    private RuleType ruleType;
    private Component component;
    private Boolean legal;
    private Boolean alive = Boolean.TRUE;
    private Integer accoId;
    private Integer tenaId;
    private LocalDateTime modificationTime;
    private LocalDateTime destructionTime;

    // interna
    private SubComponent subComponent;
    private String description;
    private LocalDateTime creationTime = LocalDateTime.now();

    // legal
    private String number;
    private LocalDate datePublication;
    private Integer organismIssuingId;
    private Integer organismInspectorId;
    private String normLink; 
    

    public Rule(Integer normId) {
        this.normId = normId;
    }
    
    public void setOrganismIssuingId(Integer organismIssuingId ) {
        if(organismIssuingId == 0) {
            this.organismIssuingId = null;
        }
        else {
            this.organismIssuingId = organismIssuingId; 
        }
    }
    
    public void setOrganismInspectorId(Integer organismInspectorId ) {
        if(organismInspectorId == 0) {
            this.organismInspectorId = null;
        }
        else {
            this.organismInspectorId = organismInspectorId;
        }
    }

}
