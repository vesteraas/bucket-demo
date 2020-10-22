package no.werner.bucketdemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SMS {
    private String shortNumber;
    private String from;
    private String to;
    private String message;
}
