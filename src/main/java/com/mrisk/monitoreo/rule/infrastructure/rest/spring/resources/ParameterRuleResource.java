package com.mrisk.monitoreo.rule.infrastructure.rest.spring.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mrisk.monitoreo.rule.application.service.ParameterRuleService;
import com.mrisk.monitoreo.rule.domain.ParameterRule;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto.ParameterRuleDTO;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.mapper.Converter;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ParameterRuleResource {

    private final ParameterRuleService parameterRuleService;

    /**
     * Metodo para agregar parametros a una norma interna
     * 
     * @param ruleId
     * @param listRuleDTO
     * @return
     */
    @ApiOperation(value = "create parameters rule")
    @PostMapping(value = "rules/{ruleId}/parameters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveRule( @RequestHeader("X-Tena-Id") Integer tenaId,@PathVariable Integer ruleId,
            @RequestBody List<ParameterRuleDTO> listRuleDTO) {
        parameterRuleService.saveParameterRule(tenaId,ruleId,
                listRuleDTO.stream().map(paraRule -> Converter.getMapper().map(paraRule, ParameterRule.class))
                        .collect(Collectors.toList()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Metodo para obtener los parametros de una norma interna
     * 
     * @param ruleId
     * @return
     */
    @ApiOperation(value = "get parameters rule by ruleId")
    @GetMapping(value = "/rules/{ruleId}/parameters", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<ParameterRuleDTO>>> findParametersRules( @RequestHeader("X-Tena-Id") Integer tenaId,@PathVariable Integer ruleId) {

        List<EntityModel<ParameterRuleDTO>> listParameters = parameterRuleService.findParametersRuleByRuleId(tenaId,ruleId)
                .stream().map(parameter -> EntityModel.of(Converter.getMapper().map(parameter, ParameterRuleDTO.class)))
                .collect(Collectors.toList());
        return new ResponseEntity<>(listParameters, HttpStatus.OK);
    }
}
