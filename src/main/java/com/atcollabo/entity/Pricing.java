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
public class Pricing extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prcPk;
	
	@Comment("대상 pk for course, newsevent")
	@Column(nullable = false, unique = false)
	private Long targetId;

	@Comment("regular price to pay")
	@Column(nullable = false, unique = false)
	private Integer prcValue;
	
	@Comment("special price by sales")
	@Column(nullable = false, unique = false)
	private Integer prcSpecial;

	@Comment("pricing type")
	@Column(nullable = false, unique = false)
	private String prcType;

	@Comment("할인 적용 내용 % 또는 금액 등 짧게 입력")
	@Column(nullable = false, unique = false)
	private String prcSales;	

	
}
