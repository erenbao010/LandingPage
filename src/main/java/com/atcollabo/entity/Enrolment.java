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
public class Enrolment extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrolPk;
	
	@Comment("가입자 신청 시 pk of member")
	@Column(nullable = true, unique = false)
	private Long mbrId;
	
	@Comment("신청 대상 pk")
	@Column(nullable = true, unique = false)
	private Long targetId;

	@Comment("신청 대상 유형")
	@Column(nullable = true, unique = false)
	private String targetType;

	@Comment("신청 대상 - 과정,행사")
	@Column(nullable = true, unique = false)
	private String enrolFor;

	@Column(nullable = false, unique = false)
	private String enrolName;
	
	@Column(nullable = false, unique = false)
	private String enrolEmail;
	
	@Column(nullable = false, unique = false)
	private String enrolPhone;
	
	@Comment("신청자 나이(대)")
	@Column(nullable = true, unique = false)
	private String enrolAge;

	@Comment("예비 - 입금액 of 과정,행사")
	@Column(nullable = true, unique = false)
	private String enrolPrice;

	@Column(columnDefinition="TEXT", nullable = true, unique = false)
	private String enrolMemo;
	

	
}
