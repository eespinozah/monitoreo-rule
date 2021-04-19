package com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParameterDiscreteDTO{
    
    private Integer discId;
    private String name;
    private boolean outNorm;

}
