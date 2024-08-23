package com.usmobile.services.dataUsageService.repository;

import com.usmobile.services.dataUsageService.data.DailyUsageDTO;
import com.usmobile.services.dataUsageService.domain.DailyUsage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface DailyUsageRepository extends MongoRepository<DailyUsage, String> {

    Page<DailyUsage> findDailyUsagesByUserIdAndMdnAndUsageDateBetween(String userId,
                                                                      String mdn,
                                                                      LocalDateTime startDate,
                                                                      LocalDateTime endTime,
                                                                      Pageable pageable);
}
