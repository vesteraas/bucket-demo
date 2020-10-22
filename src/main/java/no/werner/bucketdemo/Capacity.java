package no.werner.bucketdemo;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;

import java.time.Duration;

public enum Capacity {
    LOUSY(1, Duration.ofDays(1)),
    BRONZE(5, Duration.ofSeconds(1)),
    SILVER(10, Duration.ofSeconds(1)),
    GOLD(20, Duration.ofSeconds(1)),
    PLATINUM(50, Duration.ofSeconds(1));

    private int messagesPerSeconds;
    private Duration duration;

    Capacity(int messagesPerSeconds, Duration duration) {
        this.messagesPerSeconds = messagesPerSeconds;
        this.duration = duration;
    }

    public Bandwidth getMessagesPerSeconds() {
        return Bandwidth.classic(messagesPerSeconds, Refill.intervally(messagesPerSeconds, duration));
    }

    static Capacity resolve(String name) {
        if (name.startsWith("BRONZE")) {
            return BRONZE;
        } else if (name.startsWith("SILVER")) {
            return SILVER;
        } else if (name.startsWith("GOLD")) {
            return GOLD;
        } else if (name.startsWith("PLATINUM")) {
            return PLATINUM;
        }

        return LOUSY;
    }
}
