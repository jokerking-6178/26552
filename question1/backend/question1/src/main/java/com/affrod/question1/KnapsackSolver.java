package com.affrod.question1;

import com.affrod.question1.Dto.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class KnapsackSolver {

    private KnapsackSolver() {
    }

    public static KnapsackResult solve(List<Vehicle> vehicles, int mechanicHours) {
        int n = vehicles.size();
        int capacity = mechanicHours;

        if (n == 0 || capacity <= 0) {
            return new KnapsackResult(new ArrayList<>(), 0, 0);
        }

        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            Vehicle v = vehicles.get(i - 1);
            int weight = v.getDuration();
            int value  = v.getImpact();

            for (int w = 0; w <= capacity; w++) {
                dp[i][w] = dp[i - 1][w];
                if (weight <= w) {
                    int withItem = dp[i - 1][w - weight] + value;
                    if (withItem > dp[i][w]) {
                        dp[i][w] = withItem;
                    }
                }
            }
        }

        List<Vehicle> selected = new ArrayList<>();
        int w = capacity;
        for (int i = n; i >= 1; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                Vehicle v = vehicles.get(i - 1);
                selected.add(0, v);
                w -= v.getDuration();
            }
        }

        int totalImpact = dp[n][capacity];
        int totalHours  = selected.stream().mapToInt(Vehicle::getDuration).sum();


        return new KnapsackResult(selected, totalImpact, totalHours);
    }

    public record KnapsackResult(
            List<Vehicle> selectedVehicles,
            int totalImpact,
            int totalHoursUsed
    ) {}
}