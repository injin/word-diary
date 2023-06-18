package project.diary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.diary.dto.SenderDto;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.SendEmailResponse;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SesService {

    private final SesClient sesClient;

    public boolean send(String subject, String content, List<String> toEmail) {

        SenderDto senderDto = SenderDto.builder()
                .from("no-reply@word-diary.com")
                .to(toEmail)
                .subject(subject)
                .content(content)
                .build();
        SendEmailResponse sesResponse = sesClient.sendEmail(senderDto.toSendRequestDto());
        boolean result = sesResponse.sdkHttpResponse().isSuccessful();
        log.info("AWS SES send email = {} result = {}", toEmail.get(0), result);
        return result;
    }

}
