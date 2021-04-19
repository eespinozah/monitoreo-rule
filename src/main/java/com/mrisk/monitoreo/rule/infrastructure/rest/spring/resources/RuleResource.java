package com.mrisk.monitoreo.rule.infrastructure.rest.spring.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mrisk.monitoreo.rule.application.service.RuleService;
import com.mrisk.monitoreo.rule.domain.Rule;
import com.mrisk.monitoreo.rule.domain.RuleFilter;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto.RuleRqDTO;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.dto.RuleRsDTO;
import com.mrisk.monitoreo.rule.infrastructure.rest.spring.mapper.Converter;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RuleResource {

    @Autowired
    private RuleService ruleService;


    /**
     * Metodo que permite encontrar una norma por filtros 
     * @param tenaId
     * @param accoId
     * @param name
     * @param normValue
     * @param typeId
     * @param compId
     * @return
     */
    @ApiOperation(value = "find rules by filters ")
    @GetMapping(value = "/rules", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityModel<RuleRsDTO>>> getRulesByFilters(@RequestHeader("X-Tena-Id") Integer tenaId,
            @RequestHeader("X-Acco-Id") Integer accoId,
            @RequestParam(required = false) String name, @RequestParam(required = false) Integer normValue,
            @RequestParam(required = false) Integer typeId, @RequestParam(required = false) Integer compId) {

        RuleFilter ruleFilter = new RuleFilter();
        ruleFilter.setAccoId(accoId);
        ruleFilter.setTenaId(tenaId);
        ruleFilter.setCompId(compId);
        ruleFilter.setNormName(name);
        ruleFilter.setNormValue(normValue);
        ruleFilter.setTypeId(typeId);
        List<EntityModel<RuleRsDTO>> listRules = ruleService.getRulesByFilters(ruleFilter).stream()
                .map(rule -> EntityModel.of(Converter.getMapper().map(rule, RuleRsDTO.class)))
                .collect(Collectors.toList());

        return new ResponseEntity<>(listRules, HttpStatus.OK);
    }

    /**
     * Metodo que permite generar un reporte según filtros indicados
     * en formato base64
     * @param tenaId
     * @param accoId
     * @param locale
     * @param name
     * @param normValue
     * @param typeId
     * @param compId
     * @return
     */
    @ApiOperation(value = "get report rules by filters ")
    @GetMapping(value = "/rules/report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateExcelReportRules(@RequestHeader("X-Tena-Id") Integer tenaId,
            @RequestHeader("X-Acco-Id") Integer accoId, @RequestHeader("X-locale") String locale,
            @RequestParam(required = false) String name, @RequestParam(required = false) Integer normValue,
            @RequestParam(required = false) Integer typeId, @RequestParam(required = false) Integer compId) {

        RuleFilter ruleReport = new RuleFilter();
        ruleReport.setAccoId(accoId);
        ruleReport.setTenaId(tenaId);
        ruleReport.setCompId(compId);
        ruleReport.setNormName(name);
        ruleReport.setNormValue(normValue);
        ruleReport.setTypeId(typeId);
        return new ResponseEntity<>(ruleService.generateReportExcelEncode(ruleReport, locale), HttpStatus.OK);
    }

    /**
     * Metodo que permite buscar una norma por id, tenaId, accoId
     * 
     * @param ruleDTO
     * @return
     */
    @ApiOperation(value = "find rule by id ")
    @GetMapping(value = "/rules/{ruleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<RuleRsDTO>> getRuleById(@RequestHeader("X-Tena-Id") Integer tenaId,
            @RequestHeader("X-Acco-Id") Integer accoId, @PathVariable Integer ruleId) {

        RuleRsDTO ruleCreated = Converter.getMapper().map(ruleService.findRuleById(tenaId, accoId, ruleId),
                RuleRsDTO.class);

        EntityModel<RuleRsDTO> resource = EntityModel.of(ruleCreated);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    /**
     * Metodo que permite crear una norma interna
     * 
     * @param ruleDTO
     * @return
     */
    @ApiOperation(value = "create internal rule")
    @PostMapping(value = "/rules", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<RuleRqDTO>> saveRule(@RequestHeader("X-Tena-Id") Integer tenaId,
            @RequestHeader("X-Acco-Id") Integer accoId, @RequestBody RuleRqDTO ruleDTO) {

        Rule rule = Converter.getMapper().map(ruleDTO, Rule.class);
        rule.setAccoId(accoId);
        rule.setTenaId(tenaId);

        RuleRqDTO ruleCreated = Converter.getMapper().map(ruleService.saveRule(rule), RuleRqDTO.class);

        EntityModel<RuleRqDTO> resource = EntityModel.of(ruleCreated);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    /**
     * Se desactiva una norma interna alive = false
     * 
     * @param tenaId
     * @param accoId
     * @param ruleId
     * @return
     */
    @ApiOperation(value = "delete internal rule")
    @DeleteMapping(value = "/rules/{ruleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<RuleRsDTO>> deleteRuleById(@RequestHeader("X-Tena-Id") Integer tenaId,
            @RequestHeader("X-Acco-Id") Integer accoId, @PathVariable Integer ruleId) {

        RuleRsDTO ruleCreated = Converter.getMapper().map(ruleService.deleteInternalRuleById(tenaId, accoId, ruleId),
                RuleRsDTO.class);

        EntityModel<RuleRsDTO> resource = EntityModel.of(ruleCreated);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

}
