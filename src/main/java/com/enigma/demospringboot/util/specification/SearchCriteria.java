package com.enigma.demospringboot.util.specification;

import com.enigma.demospringboot.util.constants.Operator;

public class SearchCriteria {
    private String key;
    private Operator operation;
    private String value;

    public SearchCriteria(String key, Operator operation, String value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Operator getOperation() {
        return operation;
    }

    public void setOperation(Operator operation) {
        this.operation = operation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
