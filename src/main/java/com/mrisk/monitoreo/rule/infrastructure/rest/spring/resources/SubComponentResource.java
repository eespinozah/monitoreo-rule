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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mrisk.monitoreo.rule.application.service.SubComponentService;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto.SubComponentDTO;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.mapper.Converter;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SubComponentResource {

    private final SubComponentService subComponentService;

    /**
     * Metodo para obtener los un subcomponente de un componente
     * 
     * @param id
     * @param csubid
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find subcomponents by component ID and subcomponentId")
    @GetMapping(value = "components/{id}/subcomponents/{csubid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<SubComponentDTO>> findSubComponentById(@RequestHeader("X-Tena-Id") Integer tenaId,
            @PathVariable Integer id, @PathVariable Integer csubid) {

        SubComponentDTO subComponent = Converter.getMapper()
                .map(subComponentService.findSubCompByCompIdAndSubId(tenaId, id, csubid), SubComponentDTO.class);
        EntityModel<SubComponentDTO> entityResponse = EntityModel.of(subComponent);
        return new ResponseEntity<>(entityResponse, HttpStatus.OK);
    }

    /**
     * Metodo para obtener los subcomponentes por id de componente
     * 
     * @param id
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find subcomponents by component ID")
    @GetMapping(value = "/components/{id}/subcomponents", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<SubComponentDTO>>> findAllSubComponentByComponentId(
            @RequestHeader("X-Tena-Id") Integer tenaId, @PathVariable Integer id) {

        List<EntityModel<SubComponentDTO>> listSubComponents = subComponentService
                .findAllSubComponentByComponentId(tenaId, id).stream()
                .map(subcomponent -> EntityModel.of(Converter.getMapper().map(subcomponent, SubComponentDTO.class),
                        linkTo(methodOn(this.getClass()).findSubComponentById(tenaId, id, subcomponent.getCsubId()))
                                .withSelfRel()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(listSubComponents, HttpStatus.OK);
    }

}
