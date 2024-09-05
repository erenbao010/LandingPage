package com.atcollabo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atcollabo.entity.NewsEvent;
import com.atcollabo.entity.SiteInfo;
import com.atcollabo.service.NewsEventService;
import com.atcollabo.service.SiteInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/blog")
@Controller
public class BlogController {
	private final NewsEventService newsService;
	private final SiteInfoService siteService;
	
	@GetMapping("/newsevent")
	public String listNewsEvent(Model model) {
		log.debug("listNewsEvent");
		List<NewsEvent> list1 = newsService.getAllNewsEvents();
		if( list1 != null && list1.size() > 0) {
			model.addAttribute("recent", list1.get(0));
		}else{
			model.addAttribute("recent", NewsEvent.builder().build() );			
		}
		model.addAttribute("newsList", list1);
		
		List<NewsEvent> list2 = newsService.getRecent(3);	
		model.addAttribute("recentnews", list2);

		SiteInfo site = siteService.getOneActive();
		model.addAttribute("site", site);		
		return "web/blog";
	}
	
	@GetMapping("/{title}")
	public String viewNewsEventByTitle(
			@PathVariable String title,
			Model model) {
		log.debug("viewNewsEventByTitle: {}", title);
		NewsEvent news = newsService.getNewsEventByTitle(title.replace("_", " "));
		model.addAttribute("news", news);
		int price = 200000;// for event, course
		model.addAttribute("price", price);

		List<NewsEvent> list1 = newsService.getRecent(3);	
		model.addAttribute("recentnews", list1);

		SiteInfo site = siteService.getOneActive();
		model.addAttribute("site", site);		
		return "web/blog-single";
	}

}
