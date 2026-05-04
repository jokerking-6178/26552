package com.affrod.question1.service;

import com.affrod.question1.Dto.Depot;
import com.affrod.question1.Dto.DepotsResponse;
import com.affrod.question1.Dto.Vehicle;
import com.affrod.question1.Dto.VehiclesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

    private final RestTemplate restTemplate;

    @Value("${api.base-url}")
    private String baseUrl;

    @Value("${api.depots-endpoint}")
    private String depotsEndpoint;

    @Value("${api.vehicles-endpoint}")
    private String vehiclesEndpoint;
    public List<Depot> fetchDepots() {
        String url = baseUrl + depotsEndpoint;

        try {
            ResponseEntity<DepotsResponse> response =
                    restTemplate.getForEntity(url, DepotsResponse.class);

            if (response.getBody() != null && response.getBody().getDepots() != null) {
                List<Depot> depots = response.getBody().getDepots();
                return depots;
            } else {
                return Collections.emptyList();
            }

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Failed to fetch depots: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error fetching depots: " + e.getMessage(), e);
        }
    }

    public List<Vehicle> fetchVehicles() {
        String url = baseUrl + vehiclesEndpoint;

        try {
            ResponseEntity<VehiclesResponse> response =
                    restTemplate.getForEntity(url, VehiclesResponse.class);

            if (response.getBody() != null && response.getBody().getVehicles() != null) {
                List<Vehicle> vehicles = response.getBody().getVehicles();
                return vehicles;
            } else {
                return Collections.emptyList();
            }

        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Failed to fetch vehicles: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error fetching vehicles: " + e.getMessage(), e);
        }
    }
}
