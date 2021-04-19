package com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParameterDto  {
	private Integer paraId;
	private String name;
	private String symbol;
	private String unit;
	private boolean discrete;

}
