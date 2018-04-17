package com.agilemonkeys.test.crm.domain.dto;

import javax.validation.constraints.NotNull;

public class CustomerDto {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
