package project.diary.dto;

import lombok.Builder;
import lombok.Getter;
import software.amazon.awssdk.services.ses.model.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class SenderDto {
    private String from;
    private List<String> to = new ArrayList<>();
    private String subject;
    private String content;

    public void addTo(String email){
        this.to.add(email);
    }

    public SendEmailRequest toSendRequestDto(){
        Destination destination = Destination.builder()
                .toAddresses(this.to)
                .build();

        Message message = Message.builder()
                .subject(createContent(this.subject))
                .body(Body.builder().html(createContent(this.content)).build())
                .build();

        return SendEmailRequest.builder()
                .source(this.from)
                .destination(destination)
                .message(message)
                .build();
    }

    private Content createContent(String text) {
        return Content.builder()
                .charset("UTF-8")
                .data(text)
                .build();
    }
}
