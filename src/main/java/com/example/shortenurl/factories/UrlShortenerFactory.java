package com.example.shortenurl.factories;

import com.example.shortenurl.models.ShortenedUrl;
import com.example.shortenurl.models.User;
import com.example.shortenurl.models.UserPlan;
import com.example.shortenurl.strategies.PlanStrategy;
import com.example.shortenurl.utils.ShortUrlGenerator;

public class UrlShortenerFactory {
    public static ShortenedUrl createUrlShortener(User user, String url) {
        UserPlan userPlan = user.getUserPlan();
        PlanStrategy planStrategy = PlanStrategyFactory.createPlanStrategy(userPlan);

        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setOriginalUrl(url);
        shortenedUrl.setShortUrl(ShortUrlGenerator.generateShortUrl());
        shortenedUrl.setUser(user);
        shortenedUrl.setExpiresAt(System.currentTimeMillis() + planStrategy.calculatePlanTime());
        return shortenedUrl;
    }
}
