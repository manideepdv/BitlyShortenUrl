package com.example.shortenurl.factories;

import com.example.shortenurl.models.UserPlan;
import com.example.shortenurl.strategies.*;

public class PlanStrategyFactory {
    public static PlanStrategy createPlanStrategy(UserPlan userPlan) {
        return switch (userPlan) {
            case FREE -> new FreePlanStrategy();
            case TEAM -> new TeamPlanStrategy();
            case BUSINESS -> new BusinessPlanStrategy();
            case ENTERPRISE -> new EnterprisePlanStrategy();
            default -> new DefaultPlanStrategy();
        };
    }
}
