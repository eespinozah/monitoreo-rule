package com.mrisk.monitoreo.rule.application.service;

import java.util.List;
import java.util.Locale;

import com.mrisk.monitoreo.rule.domain.Rule;

public interface RuleReportService {
    
    String generateRuleReportEncode(List<String> headers, List<Rule> rule);

    void setMessageSource(Locale locale); 

}
