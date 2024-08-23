package com.usmobile.services.dataUsageService.repository;

import com.usmobile.services.dataUsageService.domain.CurrentCycle;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CurrentCycleRepository extends MongoRepository<CurrentCycle, String> {

    CurrentCycle findCurrentCycleByUserIdAndMdn(String userId, String mdn);

}
