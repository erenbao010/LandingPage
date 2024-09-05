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
public class ContactInquery extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inqPk;
	
	@Comment("문의자명 ")
	@Column(nullable = false, unique = false)
	private String inqName;
	
	@Comment("문의자 이메일")
	@Column(nullable = false, unique = false)
	private String inqEmail;
	
	@Comment("문의자 제목")
	@Column(nullable = false, unique = false)
	private String inqSubject;

	@Comment("문의자 내용")
	@Column(columnDefinition="TEXT", nullable = true, unique = false)
	private String inqMessage;
	

	
}
