package com.atcollabo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atcollabo.entity.ContactInquery;

public interface ContactInqueryRepository extends JpaRepository<ContactInquery, Long> {

	List<ContactInquery> findAllByActiveOrderByInqPkDesc(boolean active);

}
