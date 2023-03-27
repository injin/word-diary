package project.diary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diary")
@Slf4j
public class DiaryController {

    @GetMapping("/step1")
    public String step1() {
        return "diary/step1";
    }

}
