package com.atcollabo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atcollabo.entity.NewsEvent;

public interface NewsEventRepository extends JpaRepository<NewsEvent, Long> {

	List<NewsEvent> findAllByActive(boolean active);

	NewsEvent findByActiveAndNewsOnBanner(boolean active, boolean onBanner);

	NewsEvent findFirstByActiveOrderByDateCreateDesc(boolean active);

	List<NewsEvent> findAllByActiveOrderByDateCreateDesc(boolean active);

	List<NewsEvent> findByActiveAndNewsTitle(boolean active, String title);

}
