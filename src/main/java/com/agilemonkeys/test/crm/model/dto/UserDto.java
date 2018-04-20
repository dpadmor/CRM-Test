package com.agilemonkeys.test.crm.model.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserDto() {
    }

    public UserDto(String usernname, String password, String name, String surname) {
        this.username = usernname;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    private String username;

    private String password;

    private String name;

    private String surname;

    private String status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus () {
        return this.status;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", status=" + status +
                '}';
    }


}
