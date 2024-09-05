package com.atcollabo.admin;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.atcollabo.entity.Enrolment;
import com.atcollabo.entity.Hackathon;
import com.atcollabo.entity.Member;
import com.atcollabo.entity.NewsEvent;
import com.atcollabo.file.FileService;
import com.atcollabo.service.EnrolmentService;
import com.atcollabo.service.HackathonService;
import com.atcollabo.service.NewsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/hackathon")
@Controller
public class HackathonController {
	private final FileService fileService;
	private final HackathonService hackathonService;
	
	@GetMapping({"","/"})
	public String hackathon(
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";
		log.debug("hackathon : {}", user.isEnabled());
		List<Hackathon> list1 = hackathonService.getList(true);
		model.addAttribute("hackathonList", list1);
		return "adm/hakathon-list";
	}

	@GetMapping("/put")
	public String hackathonForm(
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";
		log.debug("hackathonForm : {}", user.isEnabled());
		return "adm/hakathon-form";
	}

	@PostMapping("/post")
	public String hackathonPost(
			String title, String type, String start, String end, String summary,
			MultipartFile[] images,
			Model model, @AuthenticationPrincipal Member user) {
		if( user == null )	return "redirect:/logout";
		log.debug("hackathonPost : {}", user.isEnabled());
		String[] imgPath = new String[] {"","","",""};
		if(images != null && images.length > 0) {
			int i= 0;
			for(MultipartFile image : images) {
				imgPath[i] = fileService.saveFile(image, (ar)->{
					for(String s : ar) {
						log.debug("{}", s);
					}
				});
				i += 1;
				if(i==4) {
					break;
				}
			}
		}
		Hackathon hackerthon = Hackathon.builder()
				.hakTitle(title)
				.hakType(type)
				.hakStart(start)
				.hakEnd(end)
				.hakSummary(summary)
				.hakRegisters(0)
				.hakOnpass(0)
				.hakOffpass(0)
				.hakWiners(0)
				.hakThums1(imgPath[0])
				.hakThums2(imgPath[1])
				.hakThums3(imgPath[2])
				.hakThums4(imgPath[3])
				.build();
		hackerthon.onActive();
		hackathonService.newHackathon(hackerthon);
		return "redirect:/admin/hackathon/";
	}



}
