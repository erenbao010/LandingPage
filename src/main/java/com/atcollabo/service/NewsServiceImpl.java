package com.atcollabo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atcollabo.entity.NewsEvent;
import com.atcollabo.repository.NewsEventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class NewsServiceImpl implements NewsService {
	
	private final NewsEventRepository newsRepository;

	@Override
	public List<NewsEvent> getAllActive() {
		log.debug("getAllActive: ");
//		return newsRepository.findAllByActive(true);
		return newsRepository.findAllByActiveOrderByDateCreateDesc(true);
	}

	@Override
	public NewsEvent addNews(NewsEvent news) {
		news.onActive();
		return newsRepository.save(news);
	}

	@Override
	public NewsEvent getNews(long blogpk) {
		return newsRepository.findById(Long.valueOf(blogpk)).orElseGet(()->{
			log.error("getNews: not found news[{}]", blogpk);
			NewsEvent empty = NewsEvent.builder()
					.newsTitle("has no contents")
					.build();
			empty.deActive();
			return empty;
		});
	}

	@Override
	public NewsEvent changeMainBanner(long blogpk) {
		NewsEvent cur = newsRepository.findByActiveAndNewsOnBanner(true,true);
		if( cur != null ) {
			cur.notNewsOnBanner();
			newsRepository.save(cur);
		}
		NewsEvent news = getNews(blogpk);
		if( news != null && news.getActive()) {
			news.beNewsOnBanner();
			newsRepository.save(news);
		}
		return news;
	}

	@Override
	public Object removeNews(long blogpk) {
		NewsEvent news = getNews(blogpk);
		if( news.getActive()) {
			news.deActive();
			newsRepository.save(news);
		}
		return news;
	}

}
