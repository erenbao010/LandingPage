package com.atcollabo.entity;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseEntity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7912921753902269317L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mbrPk;
	
	@Column(nullable = false, unique = true)
	private String mbrId;
	@Column(nullable = false, unique = false)
	private String mbrPw;

	@Transient
	private Set<GrantedAuthority> grantedAuthorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return mbrPw;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return mbrId;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		if( active == null)	active = true;
		return active;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		if( active == null)	active = true;
		return active;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	public void setMbrPw(String mbrPw) {
		this.mbrPw = mbrPw;
	}
}
