package com.forum_hub.forum.domain.topic.response;


public record ResponseData(Long id, String message, Long topicId, Long authorId, Boolean fixed) {
    public ResponseData(Response response) {
        this(response.getId(),
                response.getMessage(),
                response.getTopic().getId(),
                response.getUserResponse().getId(),
                response.getSolved()
                );
    }
}
