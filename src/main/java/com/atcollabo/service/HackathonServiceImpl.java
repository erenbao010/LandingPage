package com.atcollabo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atcollabo.entity.Enrolment;
import com.atcollabo.entity.Hackathon;
import com.atcollabo.repository.EnrolmentRepository;
import com.atcollabo.repository.HackathonRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class HackathonServiceImpl implements HackathonService {
	private final HackathonRepository hackathonRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Hackathon> getList(Boolean active) {
		List<Hackathon> list1 = null;
		if(active == null ) {
			list1 = hackathonRepository.findAll();
		}else {
			list1 = hackathonRepository.findAllByActive(active);			
		}
		return list1;
	}

	@Override
	public Object newHackathon(Hackathon hackerthon) {
		log.debug("newHackathon: {}", hackerthon);
		Object t = hackathonRepository.save(hackerthon);
		return t;
	}

	
}
