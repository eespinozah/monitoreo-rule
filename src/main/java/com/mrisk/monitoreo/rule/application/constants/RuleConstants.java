package com.mrisk.monitoreo.rule.application.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class RuleConstants {

    public static final String DATA_NOT_FOUND = "Data not found ";
    private static final List<String> HEADERS_REPORT_RULE_OBJ = new ArrayList<>(
            Arrays.asList("id", "name", "number", "detail", "type", "component", "orgem", "date_pub"));
    public static final List<String> HEADERS_REPORT_RULE = List.of(HEADERS_REPORT_RULE_OBJ.toArray(new String[] {}));

    private RuleConstants() {

    }

}
