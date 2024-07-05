package com.forum_hub.forum.domain.topic;

import jakarta.validation.constraints.NotNull;

public record UpdateTopicData(@NotNull Long id, String title, String message) {
}
