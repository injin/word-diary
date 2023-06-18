package project.diary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public Member(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.status = MemberStatus.PENDING;
    }

    public void updateStatus(MemberStatus status) {
        this.status = status;
    }

}
