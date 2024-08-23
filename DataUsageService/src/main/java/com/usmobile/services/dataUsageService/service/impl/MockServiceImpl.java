package com.usmobile.services.dataUsageService.service.impl;

import com.usmobile.services.dataUsageService.domain.Cycle;
import com.usmobile.services.dataUsageService.repository.CycleRepository;
import com.usmobile.services.dataUsageService.service.MockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MockServiceImpl implements MockService {

    private final CycleRepository repository;
    @Override
    public boolean createPreviousCycleData() {
         List<Cycle> cycleList = new ArrayList<>();
         addCycle(cycleList,"US-5430ede9-417d-492e-be13-29200a27b3e6", "2347", 3);
         addCycle(cycleList,"US-5430ede9-417d-492e-be13-29200a27b3e6", "234", 5);
         addCycle(cycleList,"US-5430ede9-417d-492e-be13-29200a27b3e6", "243", 3);
        addCycle(cycleList,"US-b98c8d3a-f646-4325-b5b6-d27bc0917454", "3567", 5);
        addCycle(cycleList,"US-b98c8d3a-f646-4325-b5b6-d27bc0917454", "35", 4);
        addCycle(cycleList,"US-b98c8d3a-f646-4325-b5b6-d27bc0917454", "3579", 11);
         repository.saveAll(cycleList);
         return true;
    }
    private void addCycle(List<Cycle> cycleList, String userId, String mdn, int numberOfMonth){
        for(int prevMonth =1; prevMonth <= numberOfMonth; prevMonth++){
            cycleList.add(createCycle(userId, mdn, prevMonth));
        }
    }

    private Cycle createCycle(String userId, String mdn, int previousMonth){
        LocalDateTime endDate = LocalDateTime.now().minusMonths(previousMonth);
        Cycle cycle = Cycle.builder()
                .userId(userId)
                .mdn(mdn)
                .endDate(endDate)
                .startDate(endDate.minusMonths(1).plusDays(1))
                .build();
        cycle.setCycleId(cycle.generateId());
        cycle.setCreatedDate(cycle.getStartDate());
        return cycle;
    }
}
