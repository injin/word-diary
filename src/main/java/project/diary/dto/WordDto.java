package project.diary.dto;

import lombok.Data;
import project.diary.entity.Word;

@Data
public class WordDto {

    private Long wordId;
    private Long historyId;
    private String name;

    public WordDto(Word word) {
        this.wordId = word.getId();
        this.historyId = word.getHistory().getId();
        this.name = word.getName();
    }
}
