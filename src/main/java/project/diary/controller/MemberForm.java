package project.diary.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;
    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;
    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;

}
