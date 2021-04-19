package com.mrisk.monitoreo.rule.infrastructure.rest.spring.mapper;

import java.util.Objects;

import org.modelmapper.ModelMapper;

public final class Converter {
	private static ModelMapper modelMapper;

	private Converter() {
	}

	public static ModelMapper getMapper() {
		if (Objects.isNull(modelMapper)) {
			modelMapper = new ModelMapper();
		}
		return modelMapper;
	}
}
