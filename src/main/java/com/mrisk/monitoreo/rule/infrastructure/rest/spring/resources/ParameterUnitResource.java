package com.mrisk.monitoreo.rule.infrastructure.rest.spring.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrisk.monitoreo.rule.application.service.ParameterUnitService;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto.ParameterUnitDTO;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.mapper.Converter;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ParameterUnitResource {

    private final ParameterUnitService parameterUnitService;

    /**
     * Meotodo que permite obtener las unidades de los parametros
     * 
     * @return
     */
    @ApiOperation(value = "Find parameters unit")
    @GetMapping(value = "/parameters/unit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ParameterUnitDTO>> singleSelectRule() {

        List<ParameterUnitDTO> parameterUnitList = parameterUnitService.getListParameterUnit().stream()
                .map(x -> Converter.getMapper().map(x, ParameterUnitDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>((parameterUnitList), HttpStatus.OK);
    }

}
