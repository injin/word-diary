package project.diary.aws;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import project.diary.dto.SenderDto;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.SendEmailResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class AwsSESTest {

    @Autowired
    Environment env;

    //@Test
    public void sendEmail() {

        SesClient sesClient = SesClient.builder()
                .region(Region.AP_NORTHEAST_2)
                .build();

        SenderDto senderDto = SenderDto.builder()
                .from("test@gmail.com")
                .to(List.of("test@gmail.com"))
                .subject("로컬에서 SES 테스트")
                .content("로컬에서 SES 테스트")
                .build();
        SendEmailResponse sesResponse = sesClient.sendEmail(senderDto.toSendRequestDto());
        boolean result = sesResponse.sdkHttpResponse().isSuccessful();
        Assertions.assertThat(result).isTrue();

    }

}
