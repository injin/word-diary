package project.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.diary.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsMemberByEmail(String email);
    Optional<Member> findByEmail(String email);


}
