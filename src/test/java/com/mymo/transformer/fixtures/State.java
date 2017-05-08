package com.mymo.transformer.fixtures;

public enum State {
    ILLINOIS ("IL"),
    NEW_YORK ("NY");

    private final String abriviation;
    State(String abriviation) {
        this.abriviation = abriviation;
    }

    public String getAbriviation() {
        return abriviation;
    }
}
