package com.atcollabo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atcollabo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findByActiveAndMbrId(boolean active, String username);

}
