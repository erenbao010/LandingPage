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
public class SiteInfo extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sitePk;
	
	@Column(nullable = false, unique = false)
	private String siteTitle;
	
	@Column(nullable = true, unique = false)
	private String siteType;

	@Comment("근무요일")
	@Column(nullable = true, unique = false)
	private String siteWorkday;

	@Comment("근무시간")
	@Column(nullable = true, unique = false)
	private String siteWorktime;
	
	@Column(nullable = true, unique = false)
	private String sitePhone;
	
	@Column(nullable = true, unique = false)
	private String siteAddress;
	
	@Column(nullable = true, unique = false)
	private String siteEmail1;
	@Column(nullable = true, unique = false)
	private String siteEmail2;
	
	@Column(nullable = true, unique = false)
	private String siteLogo;
	@Column(nullable = true, unique = false)
	private String siteLogoHeader;
	@Column(nullable = true, unique = false)
	private String siteLogoFooter;

}
