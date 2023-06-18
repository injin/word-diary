package project.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.diary.entity.Member;
import project.diary.entity.MemberStatus;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsMemberByEmailAndStatus(String email, MemberStatus status);
    Optional<Member> findByEmail(String email);


}
