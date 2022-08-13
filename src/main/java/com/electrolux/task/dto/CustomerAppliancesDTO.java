package com.electrolux.task.dto;

import java.util.List;
import java.util.Map;

public class CustomerAppliancesDTO {

    private final Map<CustomerDTO, List<ApplianceDTO>> customerToAppliances;

    public CustomerAppliancesDTO(Map<CustomerDTO, List<ApplianceDTO>> customerToAppliances) {
        this.customerToAppliances = customerToAppliances;
    }

    public Map<CustomerDTO, List<ApplianceDTO>> getCustomerToAppliances() {
        return Map.copyOf(customerToAppliances);
    }
}
