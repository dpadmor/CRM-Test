package com.agilemonkeys.test.crm.server.resource.model.entity;


import com.sun.istack.internal.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

    public Customer (String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Customer () {};

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    private String photoUrl;



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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
