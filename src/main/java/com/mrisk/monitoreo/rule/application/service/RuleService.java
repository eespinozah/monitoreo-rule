package com.mrisk.monitoreo.rule.application.service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import com.mrisk.monitoreo.rule.application.constants.RuleConstants;
import com.mrisk.monitoreo.rule.application.exception.BusinessException;
import com.mrisk.monitoreo.rule.application.exception.DataNotFoundException;
import com.mrisk.monitoreo.rule.application.repository.RuleRepository;
import com.mrisk.monitoreo.rule.domain.Rule;
import com.mrisk.monitoreo.rule.domain.RuleFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class RuleService {

    private final RuleRepository repository;

    private final RuleInternalServiceImpl ruleInteralService;

    private final RuleReportService ruleReportService;

    public List<Rule> getRulesByFilters(RuleFilter filter) {
        List<Rule> listResponse = repository.getRulesByFilters(filter);

        if (listResponse.isEmpty()) {
            throw new DataNotFoundException(RuleConstants.DATA_NOT_FOUND);
        }
        return listResponse;
    }


    public Rule saveRule(Rule rule) {

        if (rule.getLegal().booleanValue()) {
            throw new BusinessException("legal norms cannot be created");
        }

        return ruleInteralService.saveRule(rule);

    }

    public Rule findRuleById(Integer tenaId, Integer accoId, Integer ruleId) {
        Rule ruleResponse = repository.findById(tenaId, accoId, ruleId);

        if (Objects.isNull(ruleResponse)) {
            throw new DataNotFoundException(RuleConstants.DATA_NOT_FOUND);
        }

        return ruleResponse;
    }

    public Rule deleteInternalRuleById(Integer tenaId, Integer accoId, Integer ruleId) {
        Rule ruleResponse = repository.findById(tenaId, accoId, ruleId);
        if (Objects.isNull(ruleResponse)) {
            throw new BusinessException("internal norm not found");
        }

        if (ruleResponse.getLegal().booleanValue()) {
            throw new BusinessException("You cannot delete a legal norm");
        }

        return ruleInteralService.deleteRule(ruleResponse);
    }

    public String generateReportExcelEncode(RuleFilter filter, String locale) {
        log.info("generate report ");
        List<Rule> listResponse = this.getRulesByFilters(filter);
        if (listResponse.isEmpty()) {
            throw new DataNotFoundException(RuleConstants.DATA_NOT_FOUND);
        }

        Locale local = new Locale(locale);
        ruleReportService.setMessageSource(local);

        String reportEncode = ruleReportService.generateRuleReportEncode(RuleConstants.HEADERS_REPORT_RULE,
                listResponse);
        if (Objects.isNull(reportEncode)) {
            throw new BusinessException("a problem occurred with the report generation");
        }
        return reportEncode;
    }
}
