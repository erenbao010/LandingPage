package com.atcollabo.admin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atcollabo.entity.Member;
import com.atcollabo.entity.MemberRole;
import com.atcollabo.repository.MemberRepository;
import com.atcollabo.repository.MemberRoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements UserDetailsService {
	private final MemberRepository memberRepository;
	private final MemberRoleRepository memberRoleRepository;
	private final PasswordEncoder passEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.debug("loadUserByUsername: {}", username);
		String initpass = passEncoder.encode("atc2040go");
		log.debug("loadUserByUsername: if started firts, atcadmin and {}", initpass);

		Member member = memberRepository.findByActiveAndMbrId(true, username);
		if( member != null) {
			List<MemberRole> roles = memberRoleRepository.findAllByActiveAndMbrId(true, member.getMbrPk());
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
			for( MemberRole role : roles ) {
				String roleName = role.getRoleName().toUpperCase();
				grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roleName ));
			}
			Member authmember = Member.builder()
					.mbrPk(member.getMbrPk())
					.mbrId(member.getMbrId())
					.mbrPw(member.getMbrPw())
					.grantedAuthorities(grantedAuthorities)
					.build();
			return authmember;
		}
		// null is violation
		return Member.builder().build();
	}

}
