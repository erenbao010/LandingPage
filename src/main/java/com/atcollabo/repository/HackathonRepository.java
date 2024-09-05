package com.atcollabo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atcollabo.entity.Hackathon;

public interface HackathonRepository extends JpaRepository<Hackathon, Long>{

	List<Hackathon> findAllByActive(Boolean active);

}
