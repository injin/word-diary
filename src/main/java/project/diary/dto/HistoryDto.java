package project.diary.dto;

import lombok.Data;
import project.diary.entity.History;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class HistoryDto {

    private Long historyId;
    private LocalDate targetDate;
    private String description;
    private List<WordDto> words = new ArrayList<>();

    public HistoryDto(History history) {
        this.historyId = history.getId();
        this.targetDate = history.getTargetDate();
        this.description = history.getDescription();
        this.words = history.getWords().stream().map(WordDto::new).collect(Collectors.toList());
    }
}
