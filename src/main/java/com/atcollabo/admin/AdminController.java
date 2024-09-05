package com.atcollabo.admin;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.atcollabo.entity.ContactInquery;
import com.atcollabo.entity.Enrolment;
import com.atcollabo.entity.Member;
import com.atcollabo.entity.NewsEvent;
import com.atcollabo.entity.SiteInfo;
import com.atcollabo.file.FileService;
import com.atcollabo.service.ContactInqueryService;
import com.atcollabo.service.EnrolmentService;
import com.atcollabo.service.NewsService;
import com.atcollabo.service.SiteInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {
	private final NewsService newsService;
	private final FileService fileService;
	private final EnrolmentService enrolService;
	private final SiteInfoService siteService;
	private final ContactInqueryService contactService;
	
	@GetMapping({"","/"})
	public String dash(
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";
		log.debug("dash : {}", user.isEnabled());
		List<Enrolment> list1 = enrolService.getListEnrolment();
		model.addAttribute("enrolments", list1);
		
		List<ContactInquery> list2 = contactService.getListContactInquery();
		model.addAttribute("contacts", list2);

		SiteInfo site = siteService.getOneActive();
		model.addAttribute("site", site);		
		return "adm/dash";
	}

	@GetMapping({"/blog", "/blog/"})
	public String listBlog(
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";		
		log.debug("listBlog : {}", user.isEnabled());
		List<NewsEvent> list1 = newsService.getAllActive();
		model.addAttribute("blogList", list1);
		return "adm/blog-list";
	}
	
	@PostMapping("/blog/put")
	public String putBlog(
			String title, String summary, String sendUrl, String content,
			MultipartFile thumImage, MultipartFile bannerImage,
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";		
		log.debug("putBlog: ");
		try {
			String thumPath = "";
			String bannerPath = "";
			if( thumImage != null && thumImage.getSize() > 0) {
				
				thumPath = fileService.saveFile(thumImage, (a)->{
					log.debug("{}", a);
				});
			}
			if( bannerImage != null && bannerImage.getSize() > 0) {
				
				bannerPath = fileService.saveFile(bannerImage, (a)->{
					log.debug("{}", a);
				});
			}
			NewsEvent news = NewsEvent.builder()
					.newsTitle(title)
					.newsSummary(summary)
					.newsUrl(sendUrl)
					.newsContents(content)
					.newsThums(thumPath)
					.newsBanner(bannerPath)
					.build();
			
			NewsEvent savednews = newsService.addNews(news);
			log.debug("putBlog: added news[{}]", savednews.getNewsPk());
		}catch(Exception e) {
			log.error("putBlog: exceptions {} ", e.getMessage());
		}
		return "redirect:/admin/blog";
	}

	@GetMapping("/blog/view")
	public String viewBlog(
			String id,
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";
		log.debug("listBlog : {}", user.isEnabled());
		try {
			long blogpk = Long.parseLong(id);
			NewsEvent news = newsService.getNews(blogpk);
			model.addAttribute("blog", news);
			return "adm/blog-view";
		}catch(Exception e) {
			log.error("viewBlog: exceptions {}", e.getMessage());
		}
		
		return "redirect:/admin/blog";
	}

	@PostMapping("/blog/post")
	public String postBlog(
			String id,
			String title, String summary, String sendUrl, String content,
			MultipartFile thumImage, MultipartFile bannerImage,
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";		
		log.debug("postBlog: ");
		try {
			Long pk = Long.parseLong(id);
			NewsEvent orinews = newsService.getNews(pk);
			String thumPath = orinews.getNewsThums();
			String bannerPath = orinews.getNewsBanner();
			if( thumImage != null && thumImage.getSize() > 0) {
				thumPath = fileService.saveFile(thumImage, (a)->{
					log.debug("{}", a);
				});
			}
			if( bannerImage != null && bannerImage.getSize() > 0) {				
				bannerPath = fileService.saveFile(bannerImage, (a)->{
					log.debug("{}", a);
				});
			}
			NewsEvent news = NewsEvent.builder()
					.newsPk(pk)
					.newsTitle(title)
					.newsSummary(summary)
					.newsUrl(sendUrl)
					.newsContents(content)
					.newsThums(thumPath)
					.newsBanner(bannerPath)
					.build();
			
			NewsEvent savednews = newsService.addNews(news);
			log.debug("putBlog: added news[{}]", savednews.getNewsPk());
		}catch(Exception e) {
			log.error("putBlog: exceptions {} ", e.getMessage());
		}
		return "redirect:/admin/blog";
	}

	@GetMapping("/blog/mainbanner")
	public String toggleMainBanner(
			String id,
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";
		log.debug("toggleMainBanner : {}", user.isEnabled());
		try {
			long blogpk = Long.parseLong(id);
			newsService.changeMainBanner(blogpk);
		}catch(Exception e) {
			log.error("viewBlog: exceptions {}", e.getMessage());
		}
		
		return "redirect:/admin/blog";
	}

	@GetMapping("/blog/deactive")
	public String deactiveNews(
			String id,
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";
		log.debug("deactiveNews : {}", user.isEnabled());
		try {
			long blogpk = Long.parseLong(id);
			newsService.removeNews(blogpk);
		}catch(Exception e) {
			log.error("deactiveNews: exceptions {}", e.getMessage());
		}
		
		return "redirect:/admin/blog";
	}

	@PostMapping("/site/post")
	public String postSiteInfo(
			String id,
			String title, String address, String phone, String email1,
			String type, String workday, String worktime,
			MultipartFile logo, MultipartFile logonav, MultipartFile logofoot,
			Model model, @AuthenticationPrincipal Member user) {
		log.debug("postSiteInfo: {} ", user);
		try {
			long sitepk = Long.parseLong(id);
			SiteInfo site = SiteInfo.builder()
					.sitePk(sitepk)
					.siteAddress(address)
					.siteEmail1(email1)
					.sitePhone(phone)
					.siteTitle(title)
					.siteType(type)
					.siteWorkday(workday)
					.siteWorktime(worktime)
					.siteLogo("")
					.siteLogoHeader("")
					.siteLogoFooter("")
					.build();
			siteService.updateWithId(site);
			
		}catch(Exception e) {
			log.error("postSiteInfo: exceptions {}", e.getMessage());
		}		
		
		return "redirect:/admin";
	}

}
