package project.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.diary.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
