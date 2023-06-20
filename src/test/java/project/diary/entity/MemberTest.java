package project.diary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntity() {

        Member member = new Member("testMember", "1234", "testMember@gmail.com");
        em.persist(member);

        Word word1 = new Word();
        word1.setName("커피");
        Word word2 = new Word();
        word2.setName("도전");

        History history = History.createHistory(
                member,
                LocalDate.now(),
                "오늘의 회고",
                Arrays.asList(word1, word2));
        em.persist(history);

    }

}