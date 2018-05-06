package com.agilemonkeys.test.crm.server.resource.model.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserDto() {
    }

    public UserDto(String usernname, String name, String surname) {
        this.username = usernname;
        this.name = name;
        this.surname = surname;
    }

    private String username;

    private String name;

    private String surname;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
