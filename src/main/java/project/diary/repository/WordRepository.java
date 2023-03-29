package project.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.diary.entity.Word;

public interface WordRepository extends JpaRepository<Word, Long> {

}
