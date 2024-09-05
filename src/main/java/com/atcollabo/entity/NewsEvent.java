package com.atcollabo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

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
public class NewsEvent extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsPk;
	
	@Column(nullable = false, unique = false)
	private String newsTitle;
	
	@Column(nullable = false, unique = false)
	private String newsBanner; // 배너 이미지 : 가로로 긴거

	@Column(nullable = false, unique = false)
	private boolean newsOnBanner;	// 배너 오픈 스위치

	@Column(nullable = true, unique = false)
	private String newsUrl;	// 배너 이미지 클릭 시 이동 URL

	@Column(nullable = true, unique = false)
	private String newsThums;	// 대표이미지

	@Column(columnDefinition="TEXT", nullable = true, unique = false)
	private String newsSummary;	// 내용 요약 text

	@Column(columnDefinition="LONGTEXT", nullable = true, unique = false)
	@Lob
	private String newsContents;	// 내용 html

	@Comment("category of blog/news")
	@Column(nullable = true, unique = false)
	private String newsCategory;

//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return active;
//	}

	public void setNewsPk(Long newsPk) {
		this.newsPk = newsPk;
	}

	public void notNewsOnBanner() {
		this.newsOnBanner = false;		
	}

	public void beNewsOnBanner() {
		this.newsOnBanner = true;
	}
	
	public String shortTitle() {
		if( this.newsTitle.length() >= 16) {
			String title = this.newsTitle.substring(0, 15);
			title += "...";
			return title;
		}
		return this.newsTitle;
	}
}
