package project.diary.auth;

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
            errors.rejectValue("email","이메일 중복 오류", "이미 사용 중인 이메일입니다.");
        } else if (memberRepositoryCustom.existsPendingMember(form.getEmail()).isPresent()) {
            errors.reject("이메일 인증 필요", "이메일 인증을 완료해 주세요.");
        }
    }
}
