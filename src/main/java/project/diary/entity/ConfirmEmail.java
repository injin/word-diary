package project.diary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime dateExpired;
    private String email;
    private String securityKey;


}
