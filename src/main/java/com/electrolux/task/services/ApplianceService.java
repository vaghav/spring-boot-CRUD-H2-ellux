package com.electrolux.task.services;

import java.util.List;
import java.util.Optional;

import com.electrolux.task.dto.CustomerDTO;
import com.electrolux.task.model.Appliance;

public interface ApplianceService {

    /**
     * Creates the appliance by given parameters.
     * @param dtos the data to create the appliances
     */
    void createAppliance(List<CustomerDTO> dtos);

    /**
     * Returns the appliance by given id.
     * @param id the id of the appliance
     * @return the appliance
     */
    Optional<Appliance> getApplianceStatusById(long id);

    /**
     * Updates the status of the appliance.
     * @param id the id of the appliance
     * @return the updated status of the appliance
     */
    String updateStatus(long id);
}
