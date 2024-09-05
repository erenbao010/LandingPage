package com.atcollabo.api;

import com.atcollabo.entity.Enrolment;
import com.atcollabo.entity.Member;
import com.atcollabo.service.EnrolmentService;
import com.atcollabo.service.MemberService;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
//@CrossOrigin(origins = "http://localhost") // 컨트롤러에서 설정
@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private EnrolmentService enrolService;

    @PostMapping("/register")
    public ResponseEntity<Member> registerMember(@RequestBody Member registration) {
        Member registeredMember = memberService.registerNewMember(registration);
        return ResponseEntity.ok(registeredMember);
    }

    @PostMapping("/enrolment")
    public ResponseEntity<Member> enrolMember(
    		String enrolFor, String enrolName, String enrolPhone, String enrolEmail,
    		String enrolAge, String enrolJob, MultipartFile enrolFile
    		) {
    	log.debug("enrolMember: {} / {} ", enrolName, enrolPhone);
    	
    	Enrolment enrolment = enrolService.addEnrolment(
    			Enrolment.builder()
    			.enrolFor(enrolFor)
    			.enrolName(enrolName)
    			.enrolPhone(enrolPhone)
    			.enrolEmail(enrolEmail)
    			.build()
    	);
    	
        return ResponseEntity.ok(Member.builder().build());
    }

}