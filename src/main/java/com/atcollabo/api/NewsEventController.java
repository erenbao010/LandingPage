package com.atcollabo.api;

import com.atcollabo.entity.NewsEvent;
import com.atcollabo.service.NewsEventService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
//@CrossOrigin(origins = "http://localhost") // 컨트롤러에서 설정
@RestController
@RequestMapping("/api/news-events")
public class NewsEventController {
    @Autowired
    private NewsEventService newsEventService;

    @GetMapping
    public List<NewsEvent> getAllNewsEvents() {
    	log.debug("getAllNesEvents: ");
        return newsEventService.getAllNewsEvents();
    }

    @GetMapping("/recent")
    public ResponseEntity<NewsEvent> getRecentNewsEvent() {
        NewsEvent newsEvent = newsEventService.getLastOne();
        if (newsEvent != null) {
            return ResponseEntity.ok(newsEvent);
        } else {
        	log.error("getRecentNewsEventById: not found post");
            return ResponseEntity.ok(NewsEvent.builder().build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsEvent> getNewsEventById(@PathVariable Long id) {
        NewsEvent newsEvent = newsEventService.getNewsEventById(id);
        if (newsEvent != null) {
            return ResponseEntity.ok(newsEvent);
        } else {
        	log.error("getNewsEventById: not found post[{}]", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<NewsEvent> createNewsEvent(@RequestBody NewsEvent newsEvent) {
        NewsEvent createdEvent = newsEventService.createNewsEvent(newsEvent);
        return ResponseEntity.created(URI.create("/news-events/" + createdEvent.getNewsPk())).body(createdEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsEvent> updateNewsEvent(@PathVariable Long id, @RequestBody NewsEvent newsEvent) {
        if (newsEventService.exists(id)) {
            newsEvent.setNewsPk(id);
            NewsEvent updatedEvent = newsEventService.updateNewsEvent(newsEvent);
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewsEvent(@PathVariable Long id) {
        if (newsEventService.exists(id)) {
            newsEventService.deleteNewsEvent(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
