package project.diary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "history")
public class History extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate targetDate;
    private String description;

    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Word> words = new ArrayList<>();

    public void addWord(Word word) {
        words.add(word);
        word.setHistory(this);
    }

    public static History createHistory(Member member, LocalDate targetDate, String description, List<Word> words) {
        History history = new History();
        history.setMember(member);
        history.setTargetDate(targetDate);
        history.setDescription(description);
        for (Word word : words) {
            history.addWord(word);
        }
        return history;
    }

    public void updateWords(List<Word> words) {
        this.words.clear();
        for (Word word : words) {
            this.addWord(word);
        }
    }

}
