package com.usmobile.services.dataUsageService.service.impl;

import com.usmobile.services.dataUsageService.constant.DataUsageConstants;
import com.usmobile.services.dataUsageService.data.CycleInfoDTO;
import com.usmobile.services.dataUsageService.data.request.GetCycleHistoryRequestDTO;
import com.usmobile.services.dataUsageService.data.response.GetCycleHistoryResponseDTO;
import com.usmobile.services.dataUsageService.domain.CurrentCycle;
import com.usmobile.services.dataUsageService.domain.Cycle;
import com.usmobile.services.dataUsageService.repository.CurrentCycleRepository;
import com.usmobile.services.dataUsageService.repository.CycleRepository;
import com.usmobile.services.dataUsageService.service.CurrentCycleService;
import com.usmobile.services.dataUsageService.service.CycleService;
import com.usmobile.shared.model.data.MDNCycleEventDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CycleServiceImpl implements CycleService {

    private final CycleRepository repository;

    private final CurrentCycleService currentCycleService;

    private final CurrentCycleRepository currentCycleRepository;

    @Override
    public void createFirstCycleWithNewCycleDayOfMDN(MDNCycleEventDTO mdnCycleEventDTO) {
        LocalDateTime cycleStartDate = getCycleStartDate(mdnCycleEventDTO.getCycleDay());
        LocalDateTime cycleEndDate = getCycleEndDate(cycleStartDate);
        CurrentCycle currentCycle= currentCycleRepository.findCurrentCycleByUserIdAndMdn(mdnCycleEventDTO.getUserId(), mdnCycleEventDTO.getMdn());
        if(ObjectUtils.isNotEmpty(currentCycle)){
            updateCurrentCycleInfo(cycleEndDate, currentCycle);
        } else {
            createFirstCycle(mdnCycleEventDTO, cycleStartDate, cycleEndDate);
        }

    }

    private void createFirstCycle(MDNCycleEventDTO mdnCycleEventDTO, LocalDateTime cycleStartDate, LocalDateTime cycleEndDate) {
        Cycle cycle = Cycle.builder()
                .mdn(mdnCycleEventDTO.getMdn())
                .userId(mdnCycleEventDTO.getUserId())
                .endDate(cycleEndDate)
                .startDate(cycleStartDate)
                .build();
        cycle.setCycleId(cycle.generateId());
        cycle.setCreatedDate(LocalDateTime.now());
        repository.save(cycle);
        currentCycleService.updateCurrentCycle(cycle);
        log.info("cycle {} is saved ", cycle  );
    }

    private void updateCurrentCycleInfo(LocalDateTime cycleEndDate, CurrentCycle currentCycle) {
        Cycle cycle = repository.findCycleByCycleId(currentCycle.getCurrentCycleId());
        cycle.setEndDate(cycleEndDate);
        cycle.setUpdatedDate(LocalDateTime.now());
        repository.save(cycle);
        currentCycle.setEndDate(cycleEndDate);
        currentCycle.setUpdatedDate(LocalDateTime.now());
        currentCycleRepository.save(currentCycle);
        log.info("cycle day {} is updated ", cycle );
    }

    private LocalDateTime getCycleEndDate(LocalDateTime cycleStartDate) {
        return cycleStartDate.plusMonths(1).minusDays(1)
                .withHour(DataUsageConstants.CurrentCycle.CycleEndDate.HOUR)
                .withMinute(DataUsageConstants.CurrentCycle.CycleEndDate.MINUTE)
                .withSecond(DataUsageConstants.CurrentCycle.CycleEndDate.SECOND);
    }

    private LocalDateTime getCycleStartDate(int cycleDay) {
        int month = LocalDateTime.now().getMonthValue();
        int year = LocalDateTime.now().getYear();
        return LocalDateTime.of(year,month,cycleDay,
                DataUsageConstants.CurrentCycle.CycleStartDate.HOUR,
                DataUsageConstants.CurrentCycle.CycleStartDate.MINUTE,
                DataUsageConstants.CurrentCycle.CycleStartDate.SECOND);
    }

    @Override
    public GetCycleHistoryResponseDTO getCycleHistoryOfMDN(GetCycleHistoryRequestDTO requestDTO) {
        //validate request
        List<Cycle> cycleList = repository.findCyclesByUserIdAndMdn(requestDTO.getUserId(), requestDTO.getMdn());
        return GetCycleHistoryResponseDTO.builder()
                .cycleInfoDTOList(cycleList.stream().map(cycle -> CycleInfoDTO.builder()
                        .cycleId(cycle.getCycleId())
                        .startDate(cycle.getStartDate())
                        .endDate(cycle.getEndDate())
                        .build()).collect(Collectors.toList()))
                .build();
    }

    @PostConstruct
    private void updateCycleDocument(){
        putOnScheduleCycleUpdate();
    }

    private void putOnScheduleCycleUpdate() {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        log.info("hello********** {}", LocalDateTime.now());
        Long midnight=LocalDateTime.now().until(LocalDateTime.now().plusDays(1).withHour(0).withMinute(30), ChronoUnit.MINUTES);
        //scheduler.schedule(this::updateCycleInfo, computeNextDelay(15,56, 50),TimeUnit.SECONDS );
        scheduler.scheduleAtFixedRate(this::updateCycleInfo, midnight, TimeUnit.DAYS.toMinutes(1), TimeUnit.MINUTES);

    }

   /* private static long computeNextDelay(int targetHour, int targetMin, int targetSec) {
        ZonedDateTime zonedNow = istanbulDateTime();
        ZonedDateTime zonedNextTarget = zonedNow.withHour(targetHour).withMinute(targetMin).withSecond(targetSec).withNano(0);

        if (zonedNow.compareTo(zonedNextTarget) > 0) {
            zonedNextTarget = zonedNextTarget.plusDays(1);
        }

        Duration duration = Duration.between(zonedNow, zonedNextTarget);
        return duration.getSeconds();
    }*/
    public static ZonedDateTime istanbulDateTime() {
        return ZonedDateTime.now(ZoneId.of("Europe/Istanbul"));
    }

    private void updateCycleInfo() {
        log.info("last cycle is closed, next cycle is created");
        LocalDateTime now = LocalDateTime.now().plusMonths(1).minusDays(9);
        LocalDateTime beginningOfToday = now.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfToday = now.withHour(23).withMinute(59).withMinute(59);
        List<Cycle> cycleList = repository.findByEndDateBetween(beginningOfToday, endOfToday);
        List<Cycle> newCycleList = cycleList.stream()
                .map(cycle -> createNextCycle(cycle,beginningOfToday))
                .toList();
        repository.saveAll(newCycleList);
        log.info("last cycle is closed, next cycle is created");
    }

    private Cycle createNextCycle(Cycle cycle, LocalDateTime beginningOfToday) {

        Cycle nextCycle = Cycle.builder()
                .userId(cycle.getUserId())
                .mdn(cycle.getMdn())
                .startDate(beginningOfToday.plusDays(1).withMinute(1).withSecond(1))
                .endDate(getCycleEndDate(beginningOfToday.plusDays(1)))
                .build();
        nextCycle.setCycleId(nextCycle.generateId());
        nextCycle.setCreatedDate(LocalDateTime.now());
        currentCycleService.updateCurrentCycle(nextCycle);
        return nextCycle;
    }


}
