package com.mrisk.monitoreo.rule.domain;

public enum LocaleEnum {
    
    EN("en"),
    ES("es"),
    PT("pt");
    
    String value ;
    
    private LocaleEnum(String value) {
        this.value = value;
    }
    
    public static LocaleEnum fromValue(String text) {
        for (LocaleEnum b : LocaleEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

}
