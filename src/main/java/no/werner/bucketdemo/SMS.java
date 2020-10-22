package no.werner.bucketdemo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SMS {
    private String shortNumber;
    private String from;
    private String to;
    private String message;
}
