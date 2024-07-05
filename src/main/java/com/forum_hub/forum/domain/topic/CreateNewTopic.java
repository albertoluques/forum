package com.forum_hub.forum.domain.topic;

import jakarta.validation.constraints.NotNull;

public record CreateNewTopic(@NotNull String title, @NotNull String message, @NotNull Long authorId, @NotNull Long courseId) {
}
