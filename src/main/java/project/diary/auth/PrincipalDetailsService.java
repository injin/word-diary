package project.diary.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import project.diary.entity.Member;
import project.diary.entity.MemberStatus;
import project.diary.repository.MemberRepository;

import java.util.Optional;

@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Member> findMember = memberRepository.findByEmail(email);
		if (findMember.isEmpty()) {
			throw new UsernameNotFoundException("이메일을 확인해 주세요.");
		} else if (!MemberStatus.JOINED.equals(findMember.get().getStatus())) {
			throw new UsernameNotFoundException("가입완료 상태가 아닙니다.");
		}
		return new PrincipalDetails(findMember.get());
	}

}
