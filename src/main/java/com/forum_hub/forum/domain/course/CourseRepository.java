package com.forum_hub.forum.domain.course;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByName(String name);

    Page<Course> findByActiveTrue(Pageable pageable);
    Page<Course> findByActiveFalse(Pageable pageable);
}
