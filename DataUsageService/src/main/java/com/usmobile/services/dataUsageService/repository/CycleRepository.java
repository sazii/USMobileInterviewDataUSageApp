package com.usmobile.services.dataUsageService.repository;

import com.usmobile.services.dataUsageService.domain.Cycle;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.time.LocalDateTime;
import java.util.List;

public interface CycleRepository extends MongoRepository<Cycle, String> {

    Cycle findCycleByCycleId(String cycleId);

    List<Cycle> findByEndDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Cycle> findCyclesByUserIdAndMdn(String userId, String mdn);
}
