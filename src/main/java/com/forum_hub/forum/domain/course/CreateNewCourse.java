package com.forum_hub.forum.domain.course;

import jakarta.validation.constraints.NotNull;

public record CreateNewCourse(@NotNull String name, @NotNull Category category
                              ) {

}
