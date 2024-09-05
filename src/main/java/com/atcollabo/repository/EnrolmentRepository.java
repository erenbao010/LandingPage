package com.atcollabo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atcollabo.entity.Enrolment;

public interface EnrolmentRepository extends JpaRepository<Enrolment, Long> {

	List<Enrolment> findAllByActive(boolean active);

}
