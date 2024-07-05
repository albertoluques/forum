package com.forum_hub.forum.controller;

import com.forum_hub.forum.domain.topic.CreateNewTopic;
import com.forum_hub.forum.domain.topic.TopicData;
import com.forum_hub.forum.domain.topic.TopicService;
import com.forum_hub.forum.domain.topic.UpdateTopicData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    @Autowired
    private TopicService service;

    @PostMapping
    @Transactional
    public ResponseEntity createTopic(@RequestBody @Valid CreateNewTopic data) {
        var response = service.createNewTopic(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateTopic(@RequestBody @Valid UpdateTopicData data) {
        var response = service.updateTopicData(data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable("id") Long id) {
        var response = service.deleteTopic(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<TopicData>> listTopics(@PageableDefault(size = 10) Pageable pageable) {
        var response = service.topicList(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/solved")
    public ResponseEntity<Page<TopicData>> listSolvedTopics(@PageableDefault(size = 10) Pageable pageable) {
        var response = service.listSolvedTopics(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/unsolved")
    public ResponseEntity<Page<TopicData>> listUnsolvedTopics(@PageableDefault(size = 10) Pageable pageable) {
        var response = service.listUnsolvedTopics(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity showTopicResponses(@PageableDefault(size = 10) Pageable pageable, @PathVariable("id") Long id) {
        var response = service.showSelectedTopic(id, pageable);
        return ResponseEntity.ok(response);
    }
}
