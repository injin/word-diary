package project.diary.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import project.diary.controller.MemberForm;
import project.diary.repository.MemberRepository;

@RequiredArgsConstructor
@Component
public class CheckEmailValidator extends AbstractValidator<MemberForm> {


    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberForm form, Errors errors) {
        if (memberRepository.existsMemberByEmail(form.getEmail())) {
            errors.rejectValue("email","이메일 중복 오류", "이미 사용 중인 이메일입니다.");
        }
    }
}
