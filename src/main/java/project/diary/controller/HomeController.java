package project.diary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.diary.auth.CheckEmailValidator;
import project.diary.service.MemberService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final CheckEmailValidator checkEmailValidator;
    private final MemberService memberService;

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkEmailValidator);
    }

    @RequestMapping("/")
    public String home() {
        log.info("home controller 진입");
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
        System.out.println("memberForm = " + form);
        memberService.join(form.getName(), form.getPassword(), form.getEmail());

        return "redirect:/";
    }

    @GetMapping
    public String login() {
        return "auth/login";
    }

}
