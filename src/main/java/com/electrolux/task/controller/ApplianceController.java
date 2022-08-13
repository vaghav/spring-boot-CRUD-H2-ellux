package com.electrolux.task.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electrolux.task.dto.ApplianceCreateRequestDTO;
import com.electrolux.task.services.ApplianceService;

@RestController
@RequestMapping("/api")
public class ApplianceController {

    @Autowired
    private ApplianceService applianceService;

    @GetMapping("/appliance/{id}/status")
    public ResponseEntity<String> getApplianceStatusById(@PathVariable("id") long id) {
        return applianceService.getApplianceStatusById(id)
                               .map(appliance -> new ResponseEntity<>(appliance.getStatus(), OK))
                               .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

    @PostMapping("/appliance")
    public ResponseEntity<String> createAppliance(@RequestBody ApplianceCreateRequestDTO request) {
        try {
            applianceService.createAppliance(request.getCustomerDTOS());
            return new ResponseEntity<>("Appliances successfully added", CREATED);
        } catch (Exception ex) {
            // TODO: log exception root cause and message.
            return new ResponseEntity<>("Unexpected error happened on server side", INTERNAL_SERVER_ERROR);
        }
    }
}
