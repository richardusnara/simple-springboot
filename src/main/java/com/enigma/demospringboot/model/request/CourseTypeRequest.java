package com.enigma.demospringboot.model.request;

import jakarta.validation.constraints.NotBlank;

public class CourseTypeRequest {
    @NotBlank(message = "{invalid.type.required}")
    private String typeName;

    @Override
    public String toString() {
        return "CourseType{" +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
