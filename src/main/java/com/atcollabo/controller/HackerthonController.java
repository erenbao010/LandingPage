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
@RequestMapping("/hackerthon")
@Controller
public class HackerthonController {
	private final SiteInfoService siteService;
	
	@GetMapping({"","/"})
	public String hackerthonMain(
			Model model) {
		
		return "redirect:/hackerthon/codepresso";
	}
	
	@GetMapping("/codepresso")
	public String codepresso(
			Model model) {
		
		SiteInfo site = siteService.getOneActive();
		model.addAttribute("site", site);		
		return "web/hackerthon-codepresso";
	}
}
