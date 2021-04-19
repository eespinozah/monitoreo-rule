package com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto;

import lombok.Data;

@Data
public class ParameterRuleDTO {

	private ParameterDto parameter;
	private Integer limitMinimum;
	private Integer limitMaximum;
	private String breachOption;


}
