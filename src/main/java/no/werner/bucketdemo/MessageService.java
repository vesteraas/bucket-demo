package no.werner.bucketdemo;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final CapacityService capacityService;

    public void send(SMS sms) throws CapacityExceededException {
        Bucket bucket = capacityService.resolveBucket(sms.getShortNumber());
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if (!probe.isConsumed()) {
            throw new CapacityExceededException(probe.getNanosToWaitForRefill() / 1_000_000_000);
        }
    }
}
