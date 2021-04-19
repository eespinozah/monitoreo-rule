package com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleTypeDTO {
    
    private Integer typeId;
    private String name;

}
