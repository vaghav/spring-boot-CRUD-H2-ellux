package com.electrolux.task.repository;

import com.electrolux.task.model.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplianceRepository extends JpaRepository<Appliance, Long> {

    //If we want to keep history then we need to use this method
    List<Appliance> findTopByIdOrderByCreatedDesc(long id);
}
