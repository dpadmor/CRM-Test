package com.agilemonkeys.test.crm.server.resource.model.dto;

public class VersionDto {

    public VersionDto() {
    }

    public VersionDto(String version) {
        this.version = version;
    }

    private String version;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "VersionDto{" +
                "version='" + version + '\'' +
                '}';
    }
}
