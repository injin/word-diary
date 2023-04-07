package project.diary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.diary.dto.HistoryDto;
import project.diary.entity.History;
import project.diary.entity.Member;
import project.diary.entity.Word;
import project.diary.repository.HistoryRepository;
import project.diary.repository.MemberRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final MemberRepository memberRepository;

    /**
     * 히스토리 저장
     */
    @Transactional
    public Long save(Long memberId, List<String> wordStrList) throws Exception {

        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty()) {
            throw new Exception("존재하지 않는 회원입니다.");
        }

        List<Word> words = new ArrayList<>();
        for (String wordStr : wordStrList) {
            words.add(new Word(wordStr));
        }

        Optional<History> findHistory = historyRepository.findByMemberAndTargetDate(member.get(), LocalDate.now());
        if (findHistory.isPresent()) {
            // 기존 내용 수정
            History targetHistory = findHistory.get();
            targetHistory.updateWords(words);
            return targetHistory.getId();
        } else {
            // 신규 등록
            History newHistory = History.createHistory(member.get(), LocalDate.now(), "", words);
            historyRepository.save(newHistory);
            return newHistory.getId();
        }
    }


    /**
     * 히스토리 단건 조회
     */
    public History findTodayHistory(Long memberId) throws Exception {

        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty()) {
            throw new Exception("존재하지 않는 회원입니다.");
        }

        Optional<History> findHistory = historyRepository.findByMemberAndTargetDate(member.get(), LocalDate.now());
        if (findHistory.isEmpty()) {
            throw new Exception("저장된 내역이 없습니다.");
        }
        return findHistory.get();
    }


    /**
     * 히스토리 설명 업데이트
     */
    @Transactional
    public History updateDescription(Long historyId, String description) throws Exception {

        Optional<History> findHistory = historyRepository.findById(historyId);
        if (findHistory.isEmpty()) {
            throw new Exception("저장된 내역이 없습니다.");
        }
        findHistory.get().setDescription(description);
        return findHistory.get();
    }


    public List<HistoryDto> listHistoryByPeriod(Long memberId, int searchYear, int searchMonth) throws Exception {

        Optional<Member> findMember = memberRepository.findById(memberId);
        if (findMember.isEmpty()) {
            throw new Exception("존재하지 않는 회원입니다.");
        }

        YearMonth yearMonth = YearMonth.of(searchYear, searchMonth);
        LocalDate start = yearMonth.atDay(1);
        LocalDate end = yearMonth.atEndOfMonth();
        List<History> historyList = historyRepository.findByMemberAndTargetDateBetween(findMember.get(), start, end);
        return historyList.stream().map(HistoryDto::new).collect(Collectors.toList());
    }
    
    

}
