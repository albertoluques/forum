package com.forum_hub.forum.domain.course;

import jakarta.validation.constraints.NotNull;

public record UpdateCourseData(  @NotNull
                                 Long id,
                                 String name,
                                 Category category) {
}
