package com.forum_hub.forum.domain.topic.validation;

import com.forum_hub.forum.domain.course.CourseRepository;
import com.forum_hub.forum.domain.topic.CreateNewTopic;
import com.forum_hub.forum.infra.errors.IntegrityValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseExists implements TopicValidation {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void validate(CreateNewTopic createNewTopic) {
        if (createNewTopic.courseId() == null) {
            throw new IntegrityValidation("Topic should have a course assigned");
        }
        var course = courseRepository.findById(createNewTopic.courseId());
        if (!course.isPresent()) {
            throw new ValidationException("The topic you want to add doesn't exist");
        }
    }
}
