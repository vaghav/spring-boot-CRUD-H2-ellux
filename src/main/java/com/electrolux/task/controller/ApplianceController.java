package com.electrolux.task.controller;

import com.electrolux.task.dto.ApplianceCreateRequestDTO;
import com.electrolux.task.services.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

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

    @PutMapping("/appliance/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable("id") long id) {
        try {
            applianceService.save(id);
            return new ResponseEntity<>("Appliances is online", OK);
        } catch (Exception ex) {
            // TODO: log exception root cause and message.
            return new ResponseEntity<>("Unexpected error happened on server side", INTERNAL_SERVER_ERROR);
        }
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
