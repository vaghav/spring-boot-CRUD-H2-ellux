package com.electrolux.task.dto;

import java.util.List;

public class ApplianceCreateRequestDTO {

    private List<CustomerDTO> customerDTOS;

    public ApplianceCreateRequestDTO() {

    }

    public ApplianceCreateRequestDTO(final List<CustomerDTO> customerDTOS) {
        this.customerDTOS = customerDTOS;
    }

    public List<CustomerDTO> getCustomerDTOS() {
        return customerDTOS;
    }
}
