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
public class Vehicle {

    @JsonProperty("TaskID")
    private String taskId;

    @JsonProperty("Duration")
    private int duration;

    @JsonProperty("Impact")
    private int impact;
}
