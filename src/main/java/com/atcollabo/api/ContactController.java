package com.atcollabo.api;

import com.atcollabo.entity.ContactInquery;
import com.atcollabo.service.ContactInqueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@CrossOrigin(origins = "http://localhost") // 컨트롤러에서 설정
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contact")
public class ContactController {
	
	private final ContactInqueryService contactService;

    @PostMapping("/post")
    public ResponseEntity<String> contactForm(
    		String name, String email, String subject, String message
    		) {
    	log.debug("contactForm:");
    	ContactInquery contact = ContactInquery.builder()
    			.inqName(name)
    			.inqEmail(email)
    			.inqSubject(subject)
    			.inqMessage(message)
    			.build();
    	contact.onActive();
    	String responseMessage = null;
    	try {
        	contactService.addContactInquery(contact);    		
        	responseMessage = "success send message";
    	}catch(Exception e) {
    		responseMessage = e.getMessage();
    		log.error("contactForm: exceptions {} ", responseMessage);

    	}
        return ResponseEntity.ok(responseMessage);
    }

}