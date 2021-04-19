package com.mrisk.monitoreo.rule.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParameterDiscrete{
    
    private Integer discId;
    private String name;
    private boolean outNorm;
}
