package com.forum_hub.forum.domain.topic;

import java.time.LocalDateTime;

public record TopicData(Long id,
                        String title,
                        String message,
                        LocalDateTime creationDate,
                        Boolean status,
                        String author,
                        String course) {

    public TopicData(Topic topic) {
        this(topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getDateTime(),
                topic.getStatus(),
                topic.getTopicUser().getName(),
                topic.getCourse().getName()
        );
    }
}
