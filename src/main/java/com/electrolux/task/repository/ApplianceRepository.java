package com.electrolux.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electrolux.task.model.Appliance;

public interface ApplianceRepository extends JpaRepository<Appliance, Long> {

}
