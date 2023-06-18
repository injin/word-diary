package project.diary.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.diary.entity.Member;

import java.util.Optional;

@SpringBootTest
@Transactional
class MemberRepositoryCustomTest {

    @Autowired
    MemberRepositoryCustom memberRepositoryCustom;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void existsPendingMember() {
        //given
        Member member = new Member("KIM", "1234", "kim@gmail.com");
        memberRepository.save(member);
        //when
        Optional<Member> findMember = memberRepositoryCustom.existsPendingMember("kim@gmail.com");
        //then
        Assertions.assertThat(findMember).isPresent();
    }

}