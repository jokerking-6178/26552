package com.affrod.question1.controller;
import com.affrod.question1.Dto.*;
import com.affrod.question1.service.ExternalApiService;
import com.affrod.question1.service.VehicleSchedulingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicle-scheduling")
@CrossOrigin("*")
@RequiredArgsConstructor
public class VehicleSchedulingController {

    private final VehicleSchedulingService schedulingService;
    private final ExternalApiService externalApiService;

    @GetMapping("/schedule")
    public ResponseEntity<SchedulingResponse> getSchedule() {
        try {
            SchedulingResponse response = schedulingService.buildSchedule();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/depots")
    public ResponseEntity<DepotsResponse> getDepots() {
        List<Depot> depots = externalApiService.fetchDepots();
        return ResponseEntity.ok(new DepotsResponse(depots));
    }

    @GetMapping("/vehicles")
    public ResponseEntity<VehiclesResponse> getVehicles() {
        List<Vehicle> vehicles = externalApiService.fetchVehicles();
        return ResponseEntity.ok(new VehiclesResponse(vehicles));
    }
}
