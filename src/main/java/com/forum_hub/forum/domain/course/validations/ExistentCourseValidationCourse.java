package com.forum_hub.forum.domain.course.validations;

import com.forum_hub.forum.domain.course.CourseRepository;
import com.forum_hub.forum.domain.course.CreateNewCourse;
import com.forum_hub.forum.infra.errors.IntegrityValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistentCourseValidationCourse implements ValidateCourse {
    @Autowired
    CourseRepository repository;

    @Override
    public void validate(CreateNewCourse data){
        if (repository.existsByName(data.name())) {
            throw new ValidationException("This name it's already registered");
        }
        if (data.name() == null) {
            throw new IntegrityValidation("This option cannot be empty");
        }
    }
}
