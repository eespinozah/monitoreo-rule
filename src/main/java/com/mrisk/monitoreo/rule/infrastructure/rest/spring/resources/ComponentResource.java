package com.mrisk.monitoreo.rule.infrastructure.rest.spring.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

import com.mrisk.monitoreo.rule.application.service.ComponentService;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto.ComponentDTO;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.mapper.Converter;

import io.swagger.annotations.ApiOperation;

@RestController
public class ComponentResource {

    @Autowired
    private ComponentService componentService;

    /**
     * Metodo para encontrar un componente por id
     * 
     * @param id
     * @return
     */
    @ApiOperation(value = "Find component by id ")
    @GetMapping(value = "/components/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<ComponentDTO>> findComponentById(
            @RequestHeader("X-Tena-Id") Integer tenaId, @PathVariable Integer id) {

        ComponentDTO componentDto = Converter.getMapper().map(componentService.findComponentById(tenaId, id),
                ComponentDTO.class);

        EntityModel<ComponentDTO> resource = EntityModel.of(componentDto, linkTo(
                methodOn(SubComponentResource.class).findAllSubComponentByComponentId(tenaId, componentDto.getCompId()))
                        .withRel("Subcomponents"));

        return new ResponseEntity<>((resource), HttpStatus.OK);
    }

    /**
     * Metodo para obtener todos los componentes
     * 
     * @return
     */
    @ApiOperation(value = "Find all components")
    @GetMapping(value = "/components", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<ComponentDTO>>> findComponents(
            @RequestHeader("X-Tena-Id") Integer tenaId) {

        List<EntityModel<ComponentDTO>> listComponents = componentService.findComponents(tenaId).stream()
                .map(component -> EntityModel.of(Converter.getMapper().map(component, ComponentDTO.class),
                        linkTo(methodOn(ComponentResource.class).findComponentById( tenaId,
                                component.getCompId())).withSelfRel(),
                        linkTo(methodOn(SubComponentResource.class).findAllSubComponentByComponentId(tenaId,
                                component.getCompId())).withRel("Subcomponents")))
                .collect(Collectors.toList());

        return new ResponseEntity<>(listComponents, HttpStatus.OK);
    }

}