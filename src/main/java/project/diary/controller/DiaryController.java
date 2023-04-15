package project.diary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.diary.advice.Response;
import project.diary.auth.PrincipalDetails;
import project.diary.dto.HistoryDto;
import project.diary.service.HistoryService;

import java.util.List;

@Controller
@RequestMapping("/diary")
@RequiredArgsConstructor
@Slf4j
public class DiaryController {

    private final HistoryService historyService;
    private final Response response;

    @GetMapping("/step1")
    public String step1() {
        return "diary/step1";
    }

    @PostMapping("/step1/save")
    public ResponseEntity<?> saveWords(@RequestBody List<String> wordList,
                                       @AuthenticationPrincipal PrincipalDetails customUser) throws Exception {

        Long memberId = customUser.getMember().getId();
        historyService.save(memberId, wordList);
        return response.success("성공입니다.");
    }

    @GetMapping("/step2")
    public String step2(@AuthenticationPrincipal PrincipalDetails customUser, Model model) throws Exception {
        Long memberId = customUser.getMember().getId();
        model.addAttribute("history", historyService.findTodayHistory(memberId));
        return "diary/step2";
    }

    @GetMapping("/step3")
    public String step3(@AuthenticationPrincipal PrincipalDetails customUser, Model model) throws Exception {
        Long memberId = customUser.getMember().getId();
        model.addAttribute("history", historyService.findTodayHistory(memberId));
        return "diary/step3";
    }

    @PostMapping("/step3/save")
    public ResponseEntity<?> saveDescription(@RequestBody HistoryForm form) throws Exception {

        historyService.updateDescription(form.getId(), form.getDescription());
        return response.success("성공입니다");
    }

    @GetMapping("/mydiary")
    public String mydiary() {
        return "diary/mydiary";
    }

    @GetMapping("/mydiary/get")
    public ResponseEntity<?> getHistory(@RequestParam Integer searchYear,
                                        @RequestParam Integer searchMonth,
                                        @AuthenticationPrincipal PrincipalDetails customUser) throws Exception {

        List<HistoryDto> historyList = historyService.listHistoryByPeriod(customUser.getMember().getId(), searchYear, searchMonth);
        return response.success(historyList);
    }




}
