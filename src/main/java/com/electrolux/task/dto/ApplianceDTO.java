package com.electrolux.task.dto;

public class ApplianceDTO {

    private String factoryNr;

    private String type;

    private String description;

    private String status;

    public ApplianceDTO(final String factoryNr, final String type, final String description, final String status) {
        this.factoryNr = factoryNr;
        this.type = type;
        this.description = description;
        this.status = status;
    }

    public String getFactoryNr() {
        return factoryNr;
    }

    public void setFactoryNr(final String factoryNr) {
        this.factoryNr = factoryNr;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
