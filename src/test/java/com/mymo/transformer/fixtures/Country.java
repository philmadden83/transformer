package com.mymo.transformer.fixtures;

public enum Country {
    UNITED_KINGDOM ("UK"),
    AMERICA ("USA");

    private final String iosCode;

    Country(String iosCode) {
        this.iosCode = iosCode;
    }

    public String getIosCode() {
        return iosCode;
    }
}
