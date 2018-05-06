package com.agilemonkeys.test.crm.server.oauth.model.dto;

import org.hibernate.validator.constraints.NotBlank;

public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String rol;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
