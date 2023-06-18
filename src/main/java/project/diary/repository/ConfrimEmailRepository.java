package project.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.diary.entity.ConfirmEmail;

public interface ConfrimEmailRepository extends JpaRepository<ConfirmEmail, Long> {
}
