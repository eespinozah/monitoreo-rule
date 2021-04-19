package com.mrisk.monitoreo.rule.domain;

import lombok.Data;

@Data
public class ParameterRule {

	private Parameter parameter;
	private Rule rule;
	private Integer limitMinimum;
	private Integer limitMaximum;
	private String breachOption;

	private Integer tenaId;

}
