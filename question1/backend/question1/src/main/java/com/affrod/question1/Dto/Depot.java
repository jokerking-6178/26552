package com.affrod.question1.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Depot {

    @JsonProperty("ID")
    private int id;

    @JsonProperty("MechanicHours")
    private int mechanicHours;
}
