package no.werner.bucketdemo;

import lombok.Getter;

@Getter
public class CapacityExceededException extends Exception {

    private long retryAfterSeconds;

    public CapacityExceededException(long retryAfterSeconds) {
        this.retryAfterSeconds = retryAfterSeconds;
    }
}
