package com.electrolux.task.services;

import java.util.List;
import java.util.Optional;

import com.electrolux.task.dto.CustomerDTO;
import com.electrolux.task.model.Appliance;

public interface ApplianceService {

    void createAppliance(List<CustomerDTO> dtos);

    Optional<Appliance> getApplianceStatusById(long id);

    void save(long id);
}
