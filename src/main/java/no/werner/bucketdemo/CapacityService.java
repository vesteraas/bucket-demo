package no.werner.bucketdemo;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class CapacityService {

    private final List<Account> accounts;

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String shortNumber) {
        return cache.computeIfAbsent(shortNumber, this::newBucket);
    }

    private Bucket newBucket(String shortNumber) {
        final Optional<Account> optionalAccount = accounts.stream()
                .filter(account -> account.getShortNumber().equals(shortNumber))
                .findFirst();

        Capacity capacity = Capacity.resolve(optionalAccount.get().getCapacity());

        return Bucket4j.builder()
                .addLimit(capacity.getMessagesPerSeconds())
                .build();
    }
}
