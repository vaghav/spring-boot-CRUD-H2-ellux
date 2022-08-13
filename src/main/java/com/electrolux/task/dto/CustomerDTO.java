package com.electrolux.task.dto;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class CustomerDTO {

    private String name;

    private String surname;

    private String address;

    private List<ApplianceDTO> applianceDTOS;

    public CustomerDTO(String name, String surname, String address, List<ApplianceDTO> appliances) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(surname) || StringUtils.isBlank(address)) {
            throw new IllegalArgumentException("Name, surname and address must not be empty");
        }

        if (appliances.isEmpty()) {
            throw new IllegalArgumentException("Customer at least should have one appliance");
        }
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO)) return false;
        final CustomerDTO that = (CustomerDTO) o;
        return name.equals(that.name) && surname.equals(that.surname) && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, address);
    }

    public List<ApplianceDTO> getApplianceDTOS() {
        return applianceDTOS;
    }

    public void setApplianceDTOS(final List<ApplianceDTO> applianceDTOS) {
        this.applianceDTOS = applianceDTOS;
    }
}
