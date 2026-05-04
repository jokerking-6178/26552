package com.affrod.question1.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingResponse {

    private String status;
    private LocalDateTime generatedAt;
    private int totalDepots;
    private int totalVehiclesAvailable;
    private int totalVehiclesScheduled;
    private int grandTotalImpactScore;
    private List<DepotScheduleResult> depotSchedules;
}
