package com.mrisk.monitoreo.rule.infrastructure.rest.spring.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mrisk.monitoreo.rule.application.service.ParameterDiscreteService;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto.ParameterDiscreteDTO;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.mapper.Converter;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ParameterDiscreteResource {

    @Autowired
    private ParameterDiscreteService parameterDiscreteService;


    /**
     * Metodo para obtener todos los parametros discretos
     * 
     * @return
     */
    @ApiOperation(value = "Find all discrete params")
    @GetMapping(value="/parameters/{paraId}/discretes",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<ParameterDiscreteDTO>>> findComponents(@RequestHeader("X-Tena-Id") Integer tenaId,@PathVariable Integer paraId) {

        List<EntityModel<ParameterDiscreteDTO>> listParametersDiscrete = parameterDiscreteService.findParametersRuleByRuleId(tenaId,paraId).stream()
                .map(component -> EntityModel.of(Converter.getMapper().map(component, ParameterDiscreteDTO.class)))
                .collect(Collectors.toList());

        return new ResponseEntity<>(listParametersDiscrete, HttpStatus.OK);
    }

}