package project.diary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "confirm_email")
public class ConfirmEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "confirm_email_id")
    private Long id;
    private Long memberId;
    private LocalDate dateExpired;
    private String email;
    private String securityKey;

    public ConfirmEmail(Long memberId, String email) {
        this.memberId = memberId;
        this.email = email;
        this.dateExpired = LocalDate.now().plusDays(1);
        Random random = new Random();
        this.securityKey = UUID.randomUUID().toString();
    }


}
