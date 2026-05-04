package com.affrod.question1.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepotScheduleResult {

    private int depotId;
    private int availableMechanicHours;
    private int totalHoursUsed;
    private int totalImpactScore;
    private int vehiclesScheduled;
    private List<Vehicle> selectedVehicles;
}
