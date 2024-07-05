package com.forum_hub.forum.controller;

import com.forum_hub.forum.domain.topic.TopicStatusResponse;
import com.forum_hub.forum.domain.topic.response.CreateResponseData;
import com.forum_hub.forum.domain.topic.response.EditResponseData;
import com.forum_hub.forum.domain.topic.response.ResponseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/responses")
@SecurityRequirement(name = "bearer-key")
public class ResponseController {
    @Autowired
    private ResponseService service;

    @PostMapping
    @Transactional
    public ResponseEntity createResponse(@RequestBody @Valid CreateResponseData data) {
        var response = service.createResponse(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateResponse(@RequestBody @Valid EditResponseData data) {
        var response = service.editResponses(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity changeResponseStatus(@PathVariable Long id) {
        TopicStatusResponse response = service.solvedManager(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteResponse(@PathVariable Long id) {
        service.deleteResponses(id);
        return ResponseEntity.ok().build();
    }
}
