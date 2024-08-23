package com.usmobile.services.dataUsageService;

import com.usmobile.services.dataUsageService.data.request.GetCurrentCycleDailyUsageRequestDTO;
import com.usmobile.services.dataUsageService.data.request.GetCycleHistoryRequestDTO;
import com.usmobile.services.dataUsageService.domain.CurrentCycle;
import com.usmobile.services.dataUsageService.domain.Cycle;
import com.usmobile.services.dataUsageService.domain.DailyUsage;
import com.usmobile.services.dataUsageService.repository.CurrentCycleRepository;
import com.usmobile.services.dataUsageService.repository.CycleRepository;
import com.usmobile.services.dataUsageService.repository.DailyUsageRepository;
import com.usmobile.services.dataUsageService.service.CurrentCycleService;
import com.usmobile.services.dataUsageService.service.CycleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class DataUsageServiceApplicationTests {

    @MockBean
    private CycleRepository repository;
    @MockBean
    private DailyUsageRepository dailyUsageRepository;
    @MockBean
    private CurrentCycleRepository currentCycleRepository;

    @Autowired
    private CurrentCycleService currentCycleService;
    @Autowired
    private CycleService cycleService;




    @Test
    public void getCycleHistoryOfAMDNTest(){
        List<Cycle> cycleList = List.of(createCycle("US-9ddc387a-a711-4c87-86a6-d1f0b9cf518e", "243",0),
                createCycle("US-9ddc387a-a711-4c87-86a6-d1f0b9cf518e", "243", 1),
                createCycle("US-9ddc387a-a711-4c87-86a6-d1f0b9cf518e", "243", 2),
                createCycle("US-9ddc387a-a711-4c87-86a6-d1f0b9cf518e", "234", 0),
                createCycle("US-9ddc387a-a711-4c87-86a6-d1f0b9cf518e", "234", 1),
                createCycle("US-9ddc387a-a711-4c87-86a6-d1f0b9cf518e", "234", 2)
        );
        when(repository.findCyclesByUserIdAndMdn("US-9ddc387a-a711-4c87-86a6-d1f0b9cf518e", "243")).thenReturn(cycleList);
        assertEquals(6, cycleService.getCycleHistoryOfMDN(GetCycleHistoryRequestDTO.builder()
                        .mdn("243")
                        .userId("US-9ddc387a-a711-4c87-86a6-d1f0b9cf518e")
                .build()).getCycleInfoDTOList().size());

    }

    @Test
    public void getCurCycleDailyUsageHistory() throws Exception {
        /*********data***********/
        String userId = "123", mdn = "234";
        int page = 1, pageSize = 2;
        CurrentCycle currentCycle = createCurrentCycle(userId, mdn);
        List<DailyUsage> dailyUsageList = createDailyUsageList(userId, mdn);

        /***********mock query results************/
        Pageable pageable = PageRequest.of(page,pageSize);
        when(currentCycleRepository.findCurrentCycleByUserIdAndMdn(userId, mdn)).thenReturn(currentCycle);
        when(dailyUsageRepository.findDailyUsagesByUserIdAndMdnAndUsageDateBetween(userId,
                mdn,
                currentCycle.getStartDate(),
                currentCycle.getEndDate(),
                pageable)).thenReturn(new PageImpl<>(dailyUsageList));

        /*********test*************/
        assertEquals(1,currentCycleService.getDailyUsageOfMDN(GetCurrentCycleDailyUsageRequestDTO.builder()
                        .userId(userId)
                        .mdn(mdn)
                        .page(page)
                        .pageSize(pageSize)
                .build()).getDailyUsageDTOs().getSize());
    }

    private List<DailyUsage> createDailyUsageList(String userId, String mdn) {
        return List.of(DailyUsage.builder()
                .userId(userId)
                .mdn(mdn)
                .usageDate(LocalDateTime.now())
                .usedInMb(23L)
                .build());
    }

    private CurrentCycle createCurrentCycle(String userId, String mdn) {
        return CurrentCycle.builder()
                .currentCycleId("CS-123")
                .mdn(mdn)
                .userId(userId)
                .startDate(LocalDateTime.now().minusDays(1))
                .endDate(LocalDateTime.now().plusDays(23))
                .build();
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
