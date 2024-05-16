package com.example.shortenurl.strategies;

public class FreePlanStrategy implements PlanStrategy {
    @Override
    public long calculatePlanTime() {
        return 24 * 60 * 60 * 1000;
    }
}
