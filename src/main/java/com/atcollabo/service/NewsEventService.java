package com.atcollabo.service;

import com.atcollabo.entity.NewsEvent;
import com.atcollabo.repository.NewsEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NewsEventService {
    @Autowired
    private NewsEventRepository newsEventRepository;

    public List<NewsEvent> getAllNewsEvents() {
        return newsEventRepository.findAllByActiveOrderByDateCreateDesc(true);
    }

    public NewsEvent getNewsEventById(Long id) {
        return newsEventRepository.findById(id).orElse(null);
    }

    public NewsEvent createNewsEvent(NewsEvent newsEvent) {
        return newsEventRepository.save(newsEvent);
    }

    public NewsEvent updateNewsEvent(NewsEvent newsEvent) {
        return newsEventRepository.save(newsEvent);
    }

    public void deleteNewsEvent(Long id) {
        newsEventRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return newsEventRepository.existsById(id);
    }

	public NewsEvent getLastOne() {
		NewsEvent news = newsEventRepository.findFirstByActiveOrderByDateCreateDesc(true);
		return news;
	}

	public NewsEvent getNewsEventByTitle(String title) {
		List<NewsEvent> news = newsEventRepository.findByActiveAndNewsTitle(true, title);
		if( news != null & news.size() > 0) {
			return news.get(0);
		}
		return NewsEvent.builder()
				.newsTitle("Blog Details")
				.newsSummary("Build your Dream Software & Engineering Career")
				.newsThums("bg_1.jpg")
				.newsContents("")
				.build();
	}

	public List<NewsEvent> getRecent(int limit) {
		List<NewsEvent> news = newsEventRepository.findAllByActiveOrderByDateCreateDesc(true);
		if( news != null && news.size() >= limit) {
			return news.subList(0, limit);
		}
		return news;
	}
}
