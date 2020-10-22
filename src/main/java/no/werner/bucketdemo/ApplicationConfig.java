package no.werner.bucketdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfig {

    @Bean
    public List<Account> getAccounts() {
        List<Account> result = new ArrayList<>();

        result.add(Account.builder().shortNumber("20000").capacity("LOUSY").build());
        result.add(Account.builder().shortNumber("21111").capacity("MEDIOCRE").build());
        result.add(Account.builder().shortNumber("22222").capacity("MEDIOCRE").build());
        result.add(Account.builder().shortNumber("23333").capacity("BRONZE").build());
        result.add(Account.builder().shortNumber("24444").capacity("SILVER").build());
        result.add(Account.builder().shortNumber("25555").capacity("GOLD").build());
        result.add(Account.builder().shortNumber("26666").capacity("PLATINUM").build());

        return result;
    }
}
