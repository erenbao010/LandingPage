package com.atcollabo.service;

import java.util.List;

import com.atcollabo.entity.NewsEvent;

public interface NewsService {

	List<NewsEvent> getAllActive();

	NewsEvent addNews(NewsEvent news);

	NewsEvent getNews(long blogpk);

	NewsEvent changeMainBanner(long blogpk);

	Object removeNews(long blogpk);

}
