package com.atcollabo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atcollabo.entity.SiteInfo;
import com.atcollabo.service.SiteInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/services")
@Controller
public class CourseController {
    private final SiteInfoService siteService;

    @GetMapping("/{name}")
    public String courseByName(
            @PathVariable("name") String name,
            Model model) {
        log.debug("courseByName: {}", name);
        SiteInfo site = siteService.getOneActive();
        model.addAttribute("site", site);
        if ("recruit".equals(name)) {
            return "web/recruit";
        }
        if ("ems".equals(name)) {
            return "web/ems";
        } else if ("courses".equals(name)) {
            return "web/courses";
        } else {
            // Handle other cases or provide a default redirect
            return "redirect:/services";
        }
    }

}
