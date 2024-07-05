package com.forum_hub.forum.domain.topic.response;


import com.forum_hub.forum.domain.topic.Topic;
import com.forum_hub.forum.domain.user.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResponseRepository extends JpaRepository<Response, Long> {

    boolean existsByTopicAndSolved(Topic topic, boolean solved);

    boolean existsByTopicAndMessageAndUserResponse(Topic topic, String message, User authorResponse);

    Page<Response> findAllByTopic(Topic topic, Pageable pageable);

    void removeAllByTopic(Topic referenceById);
}
