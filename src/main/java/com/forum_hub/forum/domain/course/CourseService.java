package com.forum_hub.forum.domain.course;

import com.forum_hub.forum.domain.course.validations.ValidateCourse;
import com.forum_hub.forum.domain.topic.TopicData;
import com.forum_hub.forum.domain.topic.TopicRepository;
import com.forum_hub.forum.infra.errors.IntegrityValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository cRepository;
    @Autowired
    private TopicRepository tRepository;
    @Autowired
    private List<ValidateCourse> validate;

    public CourseData createNewCourse(CreateNewCourse data) {
        validate.forEach(v -> v.validate(data));
        var course = new Course(data.name(), data.category());
        cRepository.save(course);
        return new CourseData(course);
    }

    public CourseData updateCourse(UpdateCourseData data) {
        registeredCourse(data.id());
        var course = cRepository.getReferenceById(data.id());
        course.updateCourseData(data);
        return new CourseData(course);
    }

    public CourseData changeStatus(Long id) {
        registeredCourse(id);
        var course = cRepository.getReferenceById(id);
        course.setActive();
        return new CourseData(course);
    }

    public Page<CourseData> listActiveCourses(Pageable pageable) {
        return cRepository.findByActiveTrue(pageable).map(CourseData::new);
    }

    public Page<CourseData> listInactiveCourses(Pageable pageable) {
        return cRepository.findByActiveFalse(pageable).map(CourseData::new);
    }

    public Page<CourseData> listCourses(Pageable pageable) {
        return cRepository.findAll(pageable).map(CourseData::new);
    }

    public CourseTopicData showCourse(Long id, Pageable pageable) {
        registeredCourse(id);
        var course = cRepository.getReferenceById(id);
        var topics = tRepository.findAllByCourse(course, pageable).map(TopicData::new);
        return new CourseTopicData(new CourseData(course), topics);
    }

    private void registeredCourse(Long id) {
        if (id == null) {
            throw new ValidationException("You should input an id");
        }
        if (!cRepository.existsById(id)) {
            throw new IntegrityValidation("Please, input a valid id");
        }
    }


}
