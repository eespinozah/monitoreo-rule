package com.mrisk.monitoreo.rule.infrastructure.rest.spring.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrisk.monitoreo.rule.application.service.ParameterService;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto.ParameterDto;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.mapper.Converter;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ParameterResource {

    private final ParameterService parameterService;

    /**
     * Metodo para obtener un parametro segun su id
     * 
     * @param id
     * @return
     */
    @ApiOperation(value = "Find parameter by ID")
    @GetMapping(value = "/parameters/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<ParameterDto>> singleSelectRule(
            @RequestHeader("X-Tena-Id") Integer tenaId, @PathVariable Integer id) {

        ParameterDto parameterDto = Converter.getMapper().map(parameterService.singleSelectParameter(tenaId,id),
                ParameterDto.class);

        EntityModel<ParameterDto> resource = EntityModel.of(parameterDto,
                linkTo(methodOn(this.getClass()).singleSelectRule( tenaId, parameterDto.getParaId()))
                        .withSelfRel());

        return new ResponseEntity<>((resource), HttpStatus.OK);
    }

    /**
     * Metodo para filtrar parametros por id componente, subcomponete o nombre de
     * parametro
     * 
     * @param compId
     * @param csubId
     * @param name
     * @return
     */
    @ApiOperation(value = "Find parameters by filters ex compId, csubId, name ")
    @GetMapping(value = "/parameters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<ParameterDto>>> findParametersByRequestFilter(
            @RequestHeader("X-Tena-Id") Integer tenaId, @RequestParam(required = false) Integer compId,
            @RequestParam(required = false) Integer csubId, @RequestParam(required = false) String name) {

        List<EntityModel<ParameterDto>> listParameters = parameterService
                .findParametersByRequestsFilter(tenaId,compId, csubId, name).stream()
                .map(parameter -> EntityModel.of(Converter.getMapper().map(parameter, ParameterDto.class),
                        linkTo(methodOn(this.getClass()).singleSelectRule( tenaId, parameter.getParaId()))
                                .withSelfRel()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(listParameters, HttpStatus.OK);
    }

}