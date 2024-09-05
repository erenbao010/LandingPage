package com.atcollabo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atcollabo.entity.MemberRole;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long>{

	List<MemberRole> findAllByActiveAndMbrId(boolean active, Long mbrPk);

}
