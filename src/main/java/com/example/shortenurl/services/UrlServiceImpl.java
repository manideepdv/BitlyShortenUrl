package com.example.shortenurl.services;

import com.example.shortenurl.exceptions.UrlNotFoundException;
import com.example.shortenurl.exceptions.UserNotFoundException;
import com.example.shortenurl.factories.UrlShortenerFactory;
import com.example.shortenurl.models.ShortenedUrl;
import com.example.shortenurl.models.UrlAccessLog;
import com.example.shortenurl.models.User;
import com.example.shortenurl.repositories.ShortenedUrlRepository;
import com.example.shortenurl.repositories.UrlAccessLogRepository;
import com.example.shortenurl.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService {
    private final UserRepository userRepository;
    private final ShortenedUrlRepository shortenedUrlRepository;
    private final UrlAccessLogRepository urlAccessLogRepository;

    @Autowired
    public UrlServiceImpl(UserRepository userRepository, ShortenedUrlRepository shortenedUrlRepository, UrlAccessLogRepository urlAccessLogRepository) {
        this.userRepository = userRepository;
        this.shortenedUrlRepository = shortenedUrlRepository;
        this.urlAccessLogRepository = urlAccessLogRepository;
    }

    @Override
    public ShortenedUrl shortenUrl(String url, int userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        User user = optionalUser.get();

        return shortenedUrlRepository.save(UrlShortenerFactory.createUrlShortener(user, url));
    }

    @Override
    public String resolveShortenedUrl(String shortUrl) throws UrlNotFoundException {
        Optional<ShortenedUrl> optionalShortenedUrl = shortenedUrlRepository.findByShortUrl(shortUrl);
        if (optionalShortenedUrl.isEmpty()) {
            throw new UrlNotFoundException("Url not found");
        }

        ShortenedUrl shortenedUrl = optionalShortenedUrl.get();

        if(shortenedUrl.getExpiresAt() - System.currentTimeMillis() < 0){
            throw new UrlNotFoundException("Url has been expired.!");
        }


        UrlAccessLog urlAccessLog = new UrlAccessLog();
        urlAccessLog.setShortenedUrl(shortenedUrl);
        urlAccessLog.setAccessedAt(System.currentTimeMillis());
        urlAccessLogRepository.save(urlAccessLog);

        return shortenedUrl.getOriginalUrl();
    }
}
