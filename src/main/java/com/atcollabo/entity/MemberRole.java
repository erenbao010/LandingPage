package com.atcollabo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class MemberRole extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolePk;
	
	@Comment("fk to member")
	@Column(nullable = false, unique = false)
	private Long mbrId;
	
	@Column(nullable = false, unique = false)
	private String roleName;
	
	@Column(nullable = true, unique = false)
	private String roleAuth;

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return active;
	}

}
