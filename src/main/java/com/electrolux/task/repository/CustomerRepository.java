package com.electrolux.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electrolux.task.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByNameAndSurnameAndAddress(String name, String surname, String address);
}
