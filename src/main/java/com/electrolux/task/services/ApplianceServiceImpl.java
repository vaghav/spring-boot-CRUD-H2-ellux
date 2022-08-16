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
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.electrolux.task.dto.ApplianceStatus.OFFLINE;
import static com.electrolux.task.dto.ApplianceStatus.ONLINE;

@Service
public class ApplianceServiceImpl implements ApplianceService {

    private static final int PING_FREQUENCY_IN_MINUTES = 1;

    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Optional<Appliance> getApplianceStatusById(final long id) {
        return applianceRepository.findById(id);
    }

    @Override
    @Transactional
    public String updateStatus(long id) {
        Optional<Appliance> applianceOptional = applianceRepository.findById(id);
        if (applianceOptional.isPresent()) {
            Appliance appliance = applianceOptional.get();
            if (isApplianceOnline(appliance.getCreated())) {
                appliance.setStatus(ONLINE.name());
            } else {
                appliance.setStatus(OFFLINE.name());
            }
            return appliance.getStatus();
        } else {
            throw new IllegalStateException("Appliance doesn't exist");
        }
    }

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

    private static boolean isApplianceOnline(LocalDateTime created) {
        return Duration.between(created, LocalDateTime.now()).toMinutes() <= PING_FREQUENCY_IN_MINUTES;
    }

    private static Appliance convertToAppliance(ApplianceDTO dto) {
        return new Appliance(dto.getFactoryNr(), dto.getType(), dto.getDescription(), dto.getStatus(),
                LocalDateTime.now(Clock.systemUTC()));
    }
}
