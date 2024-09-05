package com.atcollabo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atcollabo.entity.Enrolment;
import com.atcollabo.repository.EnrolmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class EnrolmentServiceImpl implements EnrolmentService {

	private final EnrolmentRepository enrolRepository;

	@Override
	public Enrolment addEnrolment(Enrolment enrol) {
		enrol.onActive();
		return enrolRepository.save(enrol);
	}

	@Override
	public List<Enrolment> getListEnrolment() {
		List<Enrolment> list1 = enrolRepository.findAllByActive(true);
		return list1;
	}
}
