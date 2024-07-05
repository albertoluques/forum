package com.forum_hub.forum.domain.course;

public record CourseData(Long id, String name, Category category, Boolean active) {
    public CourseData(Course course) {
        this(course.getId(), course.getName(), course.getCategory(), course.getActive());
    }
}
