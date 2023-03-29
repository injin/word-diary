package project.diary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import project.diary.service.HistoryService;

import java.util.List;

@Controller
@RequestMapping("/diary")
@RequiredArgsConstructor
@Slf4j
public class DiaryController {

    private final HistoryService historyService;

    @GetMapping("/step1")
    public String step1() {
        return "diary/step1";
    }

    @PostMapping("/step1/save")
    public ResponseEntity<?> saveWords(@RequestBody List<String> wordList) throws Exception {

        System.out.println("파라미터 검증");
        System.out.println(wordList);
        historyService.save(6L, wordList);
        return ResponseEntity.status(HttpStatus.OK).body("성공입니다");

    }

    @GetMapping("/step2")
    public String step2() {
        return "diary/step2";
    }

}
