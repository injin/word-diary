package project.diary.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import project.diary.entity.History;
import project.diary.entity.Member;

import java.time.LocalDate;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @EntityGraph(attributePaths = {"words"})
    public Optional<History> findByMemberAndTargetDate(Member member, LocalDate targetDate);

}
