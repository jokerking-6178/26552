package com.affrod.question1.service;
import com.affrod.question1.Dto.Depot;
import com.affrod.question1.Dto.DepotScheduleResult;
import com.affrod.question1.Dto.SchedulingResponse;
import com.affrod.question1.Dto.Vehicle;
import com.affrod.question1.KnapsackSolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleSchedulingService {

    private final ExternalApiService externalApiService;

    public SchedulingResponse buildSchedule() {
        List<Depot> depots = externalApiService.fetchDepots();
        List<Vehicle> vehicles = externalApiService.fetchVehicles();

        List<DepotScheduleResult> depotResults = new ArrayList<>();
        int grandTotalImpact = 0;
        int grandTotalScheduled = 0;

        for (Depot depot : depots) {
            KnapsackSolver.KnapsackResult result = KnapsackSolver.solve(vehicles, depot.getMechanicHours());

            DepotScheduleResult scheduleResult = DepotScheduleResult.builder()
                    .depotId(depot.getId())
                    .availableMechanicHours(depot.getMechanicHours())
                    .totalHoursUsed(result.totalHoursUsed())
                    .totalImpactScore(result.totalImpact())
                    .vehiclesScheduled(result.selectedVehicles().size())
                    .selectedVehicles(result.selectedVehicles())
                    .build();

            depotResults.add(scheduleResult);
            grandTotalImpact += result.totalImpact();
            grandTotalScheduled += result.selectedVehicles().size();

        }


        return SchedulingResponse.builder()
                .status("SUCCESS")
                .generatedAt(LocalDateTime.now())
                .totalDepots(depots.size())
                .totalVehiclesAvailable(vehicles.size())
                .totalVehiclesScheduled(grandTotalScheduled)
                .grandTotalImpactScore(grandTotalImpact)
                .depotSchedules(depotResults)
                .build();
    }
}
