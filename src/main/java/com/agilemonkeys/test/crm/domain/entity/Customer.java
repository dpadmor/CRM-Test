package com.agilemonkeys.test.crm.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Customer {

    public Customer (String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Customer () {};

    @Id
    @NotEmpty
    private String id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
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

    @Override
    public String toString() {
        return "Customer { ID : " + id + ", NAME : " + name + ", SURNAME : " + surname + "}";
    }
}
