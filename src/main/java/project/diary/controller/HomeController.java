package project.diary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.diary.auth.CheckEmailValidator;
import project.diary.service.MemberService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CheckEmailValidator checkEmailValidator;
    private final MemberService memberService;

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkEmailValidator);
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "auth/join";
    }

    @PostMapping("/join")
    public String join(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "auth/join";
        }

        boolean joinResult = memberService.join(form.getName(), form.getPassword(), form.getEmail());
        if (!joinResult) {
            result.reject("joinGlobal", "가입 처리 과정에서 오류가 발생하였습니다.");
            return "auth/join";
        }

        return "redirect:/auth/confirmGuide";
    }

    @GetMapping("/auth/confirmGuide")
    public void confirmGuide() { }

    @GetMapping("/auth/confirmJoinEmail")
    public void confirmJoinEmail(@RequestParam Long memberId,
                                 @RequestParam Long confirmId,
                                 @RequestParam String key,
                                 Model model) {

        boolean result = memberService.confirmEmail(memberId, confirmId, key);
        model.addAttribute("result", result);
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "auth/login";
    }

}
