package com.forum_hub.forum.domain.course.validations;

import com.forum_hub.forum.domain.course.CreateNewCourse;
import com.forum_hub.forum.infra.errors.IntegrityValidation;
import org.springframework.stereotype.Component;

@Component
public class ValidCategory implements ValidateCourse {

    @Override
    public void validate(CreateNewCourse data) {
        if (data.category() == null) {
            throw new IntegrityValidation("Category is obligatory");
        }
    }
}
