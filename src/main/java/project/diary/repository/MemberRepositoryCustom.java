package project.diary.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import project.diary.entity.Member;
import project.diary.entity.MemberStatus;
import project.diary.entity.QMember;

import java.util.Optional;

import static project.diary.entity.QMember.member;

@Repository
public class MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    public MemberRepositoryCustom(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Optional<Member> existsPendingMember(String email) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .where(member.email.eq(email).and(member.status.eq(MemberStatus.PENDING)))
                .fetchOne());
    }

}
