package com.electrolux.task.services;

import com.electrolux.task.dto.ApplianceDTO;
import com.electrolux.task.dto.CustomerDTO;
import com.electrolux.task.model.Appliance;
import com.electrolux.task.model.Customer;
import com.electrolux.task.repository.ApplianceRepository;
import com.electrolux.task.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplianceServiceImpl implements ApplianceService {

    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public void createAppliance(List<CustomerDTO> customerDtoList) {
        for (CustomerDTO customerDTO : customerDtoList) {
            Customer customer = customerRepository.findByNameAndSurnameAndAddress(customerDTO.getName(),
                    customerDTO.getSurname(),
                    customerDTO.getAddress());
            if (customer != null) {
                throw new IllegalStateException("The customer already exists in database");
            }

            customer = new Customer(customerDTO.getName(), customerDTO.getSurname(), customerDTO.getAddress());
            List<Appliance> appliances = new ArrayList<>();
            for (ApplianceDTO dto : customerDTO.getApplianceDTOS()) {
                Appliance appliance = convertToAppliance(dto);
                appliance.setCustomer(customer);
                applianceRepository.save(appliance);
                appliances.add(appliance);
            }

            customer.addAllAppliances(appliances);
            customerRepository.save(customer);
        }
    }

    @Override
    public Optional<Appliance> getApplianceStatusById(final long id) {
        return applianceRepository.findById(id);
    }

    @Override
    public void save(long id) {
        applianceRepository.findById(id).ifPresentOrElse(appliance -> appliance.setStatus("online"),
                () -> {
                    throw new IllegalStateException("Appliance doesn't exist or it's offline");
                });
    }

    private Appliance convertToAppliance(ApplianceDTO dto) {
        return new Appliance(dto.getFactoryNr(), dto.getType(),
                dto.getDescription(), dto.getStatus());
    }
}
