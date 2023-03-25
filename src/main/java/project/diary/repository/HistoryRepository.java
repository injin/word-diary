package project.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.diary.entity.History;

public interface HistoryRepository extends JpaRepository<History, Long> {

}
