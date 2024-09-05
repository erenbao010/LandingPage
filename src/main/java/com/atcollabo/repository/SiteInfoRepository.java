package com.atcollabo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atcollabo.entity.SiteInfo;

public interface SiteInfoRepository extends JpaRepository<SiteInfo, Long>{

	List<SiteInfo> findAllByActive(Boolean active);

	List<SiteInfo> findByActive(boolean active);

}
