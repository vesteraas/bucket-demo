package no.werner.bucketdemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    public void send(SMS sms) {
        log.info("Message sent from {} to {} using short number {}", sms.getFrom(), sms.getTo(), sms.getShortNumber());
    }
}
