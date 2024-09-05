package com.atcollabo.entity;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Comment;
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
public class Hackathon extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hakPk;
	
	@Column(nullable = false, unique = false)
	private String hakTitle;
	
	@Column(nullable = false, unique = false)
	private String hakType;

	@Comment("시작일: yyyy-mm-dd")
	@Column(nullable = false, unique = false)
	private String hakStart;

	@Comment("종료일: yyyy-mm-dd")
	@Column(nullable = false, unique = false)
	private String hakEnd;

	@Comment("인원 수 현황 - 등록")
	@Column(nullable = true, unique = false)
	private Integer hakRegisters;
	@Comment("인원 수 현황 - 온라인")
	@Column(nullable = true, unique = false)
	private Integer hakOnpass;
	@Comment("인원 수 현황 - 오프라인")
	@Column(nullable = true, unique = false)
	private Integer hakOffpass;
	@Comment("인원 수 현황 - 최종")
	@Column(nullable = true, unique = false)
	private Integer hakWiners;
	
	@Column(columnDefinition="TEXT", nullable = true, unique = false)
	private String hakSummary;
	
	@Column(nullable = true, unique = false)
	private String hakThums1;
	@Column(nullable = true, unique = false)
	private String hakThums2;
	@Column(nullable = true, unique = false)
	private String hakThums3;
	@Column(nullable = true, unique = false)
	private String hakThums4;

}
