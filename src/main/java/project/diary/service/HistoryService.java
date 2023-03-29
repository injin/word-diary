package project.diary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.diary.entity.History;
import project.diary.entity.Member;
import project.diary.entity.Word;
import project.diary.repository.HistoryRepository;
import project.diary.repository.MemberRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final MemberRepository memberRepository;

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

}
