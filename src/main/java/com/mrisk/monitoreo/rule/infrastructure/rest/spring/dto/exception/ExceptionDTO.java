package com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDTO {

	private String type;
	private String description;

}
