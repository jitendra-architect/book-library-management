package com.example.book_library_management.ratelimiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RateLimiterService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // Maximum requests allowed per minute
    private static final int MAX_REQUESTS_PER_MINUTE = 5;

    // Rate limit key prefix
    private static final String RATE_LIMIT_KEY_PREFIX = "rate_limit:";

    public boolean isAllowed(String userKey) {
        String rateLimitKey = RATE_LIMIT_KEY_PREFIX + userKey;

        // Get the current count of requests
        String count = redisTemplate.opsForValue().get(rateLimitKey);

        if (count == null) {
            // If no count, initialize it and set the expiration time
            redisTemplate.opsForValue().set(rateLimitKey, "1", 1, TimeUnit.MINUTES);
            return true;
        }

        int currentCount = Integer.parseInt(count);

        if (currentCount < MAX_REQUESTS_PER_MINUTE) {
            // Increment the count
            redisTemplate.opsForValue().increment(rateLimitKey, 1);
            return true;
        }

        // Rate limit exceeded
        return false;
    }
}
