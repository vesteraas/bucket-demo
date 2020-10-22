package no.werner.bucketdemo;

import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final CapacityService capacityService;

    public void send(SMS sms) throws CapacityExceededException {
        Bucket bucket = capacityService.resolveBucket(sms.getShortNumber());

        if (!bucket.tryConsume(1)) {
            throw new CapacityExceededException();
        }
    }
}
