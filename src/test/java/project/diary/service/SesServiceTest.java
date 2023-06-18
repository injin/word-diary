package project.diary.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SesServiceTest {

    @Autowired
    private SesService sesService;

    @Test
    void mailSend(){

        boolean result = sesService.send(
                "SES 로컬 테스트 메일",
                "SES 로컬 테스트 메일 컨텐트",
                List.of("test@gmail.com"));
        assertTrue(result);
    }

}