package com.forum_hub.forum.controller;

import com.forum_hub.forum.domain.course.CourseData;
import com.forum_hub.forum.domain.course.CourseService;
import com.forum_hub.forum.domain.course.CreateNewCourse;
import com.forum_hub.forum.domain.course.UpdateCourseData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {
    @Autowired
    private CourseService service;

    @PostMapping
    @Transactional
    private ResponseEntity createCourse(@RequestBody @Valid CreateNewCourse data) {
        var response = service.createNewCourse(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateCourse(@RequestBody @Valid UpdateCourseData data) {
        var response = service.updateCourse(data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        service.changeStatus(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    @Transactional
    public ResponseEntity<Page<CourseData>> listActiveCourses(@PageableDefault(size = 10) Pageable pageable) {
        var response = service.listActiveCourses(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/inactivos")
    public ResponseEntity<Page<CourseData>> listInactiveCourses(@PageableDefault(size = 10) Pageable pageable) {
        var response = service.listInactiveCourses(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<CourseData>> listCourses(@PageableDefault(size = 10) Pageable pageable) {
        var response = service.listCourses(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity showCourses(@PageableDefault(size = 10) Pageable pageable, @PathVariable Long id) {
        var response = service.showCourse(id, pageable);
        return ResponseEntity.ok(response);
    }
}
