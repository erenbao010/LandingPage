package com.atcollabo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.atcollabo.entity.NewsEvent;
import com.atcollabo.entity.SiteInfo;
import com.atcollabo.service.NewsEventService;
import com.atcollabo.service.SiteInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
    private final NewsEventService newsService;
    private final SiteInfoService siteService;

    @GetMapping({"", "/", "/index"})
    public String index(Model model) {
        NewsEvent news = newsService.getNewsEventById(1L);
        String blogTitle = news.getNewsTitle();
        model.addAttribute("blogTitle", blogTitle.replace(" ", "_"));
        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/index";
    }

    @GetMapping({"/about"})
    public String about(Model model) {
        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/about";
    }

    @GetMapping({"/codepresso"})
    public String codepresso(Model model) {
        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/codepresso";
    }

    // add summer-codingcamp and magic-atio controller
    @GetMapping({"/magic-atio"})
    public String magicAtiso(Model model) {
        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/magic-atio";
    }

    @GetMapping({"/summer-codingcamp"})
    public String summerCodingcamp(Model model) {
        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/summer-codingcamp.html";
    }

    @GetMapping({"/contact"})
    public String contact(Model model) {
        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/contact";
    }

    @GetMapping({"/course1"})
    public String course1(Model model) {
        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/course1";
    }

    @GetMapping({"/course2"})
    public String course2(Model model) {
        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/course2";
    }

    @GetMapping({"/course3"})
    public String course3(Model model) {
        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/course3";
    }

    @GetMapping({"/courseTensorFlow2"})
    public String course6(Model model) {
        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/courseTensorFlow2";
    }

    @GetMapping({"/aiForWorker"})
    public String course4(Model model) {
        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/aiForWorker";
    }

    @GetMapping({"/javaCourse"})
    public String course5(Model model) {
        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/javaCourse";
    }

    /*
     * @see also CourseContrller
     */
    @GetMapping({"/services"})
    public String courses(Model model) {
        log.debug("services");
        List<NewsEvent> list1 = newsService.getRecent(3);
        model.addAttribute("recentnews", list1);

        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/courses";
    }

    @GetMapping({"/lectures-and-metors"})
    public String lectures_and_metors(Model model) {
        log.debug("lectures_and_metors");
        List<NewsEvent> list1 = newsService.getRecent(3);
        model.addAttribute("recentnews", list1);

        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/lectures-and-metors";
    }

    @GetMapping({"/research-and-development"})
    public String research_and_development(Model model) {
        log.debug("research_and_development");
        List<NewsEvent> list1 = newsService.getRecent(3);
        model.addAttribute("recentnews", list1);

        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/research-and-development";
    }

    @GetMapping({"/business"})
    public String business(Model model) {
        log.debug("business,collaborator");
        List<NewsEvent> list1 = newsService.getRecent(3);
        model.addAttribute("recentnews", list1);

        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/business";
    }

    /*
     * @see also BlogController
     */
    @GetMapping({"/blog"})
    public String blog(Model model) {
        log.debug("blog");
        List<NewsEvent> list1 = newsService.getRecent(3);
        model.addAttribute("recentnews", list1);

        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/blog";
    }

    /*
     * @see also BlogController
     */
    @GetMapping({"/blog-single.html"})
    public String blogSingle(
            String id,
            Model model) {
        log.debug("blogSingle");

        Long pk = Long.parseLong(id);
        NewsEvent news = newsService.getNewsEventById(pk);
        model.addAttribute("news", news);

        List<NewsEvent> list1 = newsService.getRecent(3);
        model.addAttribute("recentnews", list1);

        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        return "web/blog-single";
    }


}
