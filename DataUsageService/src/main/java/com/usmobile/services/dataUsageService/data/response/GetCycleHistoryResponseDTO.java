package com.usmobile.services.dataUsageService.data.response;

import com.usmobile.services.dataUsageService.data.CycleInfoDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetCycleHistoryResponseDTO {
    List<CycleInfoDTO> cycleInfoDTOList;
}
