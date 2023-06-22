package project.diary.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import project.diary.controller.MemberForm;
import project.diary.entity.MemberStatus;
import project.diary.repository.MemberRepository;
import project.diary.repository.MemberRepositoryCustom;

@Component
@RequiredArgsConstructor
public class CheckEmailValidator extends AbstractValidator<MemberForm> {


    private final MemberRepository memberRepository;
    private final MemberRepositoryCustom memberRepositoryCustom;

    @Override
    protected void doValidate(MemberForm form, Errors errors) {
        if (memberRepository.existsMemberByEmailAndStatus(form.getEmail(), MemberStatus.JOINED)) {
            errors.rejectValue("email","duplicate", "이미 사용 중인 이메일입니다.");
        } else if (memberRepositoryCustom.existsPendingMember(form.getEmail()).isPresent()) {
            errors.reject("pending", "이메일 인증을 완료해 주세요.");
        }
    }
}
