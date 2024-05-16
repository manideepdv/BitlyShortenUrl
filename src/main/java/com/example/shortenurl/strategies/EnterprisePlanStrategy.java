package com.example.shortenurl.strategies;

public class EnterprisePlanStrategy implements PlanStrategy {
    @Override
    public long calculatePlanTime() {
        return 365L * 24 * 60 * 60 * 1000;
    }
}
