package project.diary.config.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

@Configuration
@RequiredArgsConstructor
public class SesConfig {

    @Bean
    public SesClient sesClient(){
        return SesClient.builder()
                .region(Region.AP_NORTHEAST_2)
                .build();
    }
}
