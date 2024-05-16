package com.example.shortenurl.strategies;

public class TeamPlanStrategy implements PlanStrategy {
    @Override
    public long calculatePlanTime() {
        return 7 * 24 * 60 * 60 * 1000;
    }
}
