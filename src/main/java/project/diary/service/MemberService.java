package project.diary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.diary.entity.ConfirmEmail;
import project.diary.entity.Member;
import project.diary.entity.MemberStatus;
import project.diary.repository.ConfrimEmailRepository;
import project.diary.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    @Value("${server.host}")
    private String HOST;

    private final MemberRepository memberRepository;
    private final ConfrimEmailRepository confrimEmailRepository;
    private final SesService sesService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public boolean join(String name, String password, String email) {
        Member savedMember = memberRepository.save(new Member(name,bCryptPasswordEncoder.encode(password), email));
        ConfirmEmail confirmEmail = confrimEmailRepository.save(new ConfirmEmail(savedMember.getId(), email));
        String url = HOST + "/auth/confirmJoinEmail?memberId=" + savedMember.getId()
                   + "&confirmId=" + confirmEmail.getId()
                   + "&key=" + confirmEmail.getSecurityKey();
        String subject = "[단어 일기장] 회원가입 인증 안내";
        String content = "아래의 링크를 클릭하여 인증을 완료해 주세요. <br>";
               content += "<a href='" + url + "'>" + url + "</a>";
        boolean result = sesService.send(subject, content, List.of(email));

        return result;
    }

    @Transactional
    public boolean confirmEmail(Long memberId, Long confirmId, String key) {

        Optional<Member> findMember = memberRepository.findById(memberId);
        if (findMember.isEmpty()) return false;

        Optional<ConfirmEmail> findConfirmEmail = confrimEmailRepository.findById(confirmId);
        if (findConfirmEmail.isEmpty()) return false;

        Member member = findMember.get();
        ConfirmEmail confirmEmail= findConfirmEmail.get();
        if (MemberStatus.JOINED.equals(member.getStatus())) {
            return true;
        } else if (MemberStatus.PENDING.equals(member.getStatus())
                    && LocalDate.now().isBefore(confirmEmail.getDateExpired())) {

            if (confirmEmail.getSecurityKey().equals(key)) {
                member.updateStatus(MemberStatus.JOINED);
                return true;
            }
        }
        return false;
    }

}
