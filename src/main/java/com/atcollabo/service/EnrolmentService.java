package com.atcollabo.service;

import java.util.List;

import com.atcollabo.entity.Enrolment;

public interface EnrolmentService {

	Enrolment addEnrolment(Enrolment enrol);

	List<Enrolment> getListEnrolment();

}
