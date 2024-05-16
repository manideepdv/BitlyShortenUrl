package com.example.shortenurl.strategies;

public class BusinessPlanStrategy implements PlanStrategy {
    @Override
    public long calculatePlanTime() {
        return 30L * 24 * 60 * 60 * 1000;
    }
}
