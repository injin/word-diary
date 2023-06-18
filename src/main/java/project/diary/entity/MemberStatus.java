package project.diary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberStatus {

    PENDING(0, "가입중"),
    JOINED(1, "가입완료"),
    WITHDRAW(2, "탈퇴"),
    CANCELED(3, "가입만료");
    
    private Integer id;
    private String name;

}
