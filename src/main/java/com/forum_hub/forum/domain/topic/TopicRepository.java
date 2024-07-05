package com.forum_hub.forum.domain.topic;

import com.forum_hub.forum.domain.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Boolean existsByTitleAndMessage(String topicTitle, String topicMesage);

    void removeById(Long id);


    Page<Topic> findAllByCourse(Course course, Pageable pageable);

    Page<Topic> findAllByStatusTrue(Pageable pageable);

    Page<Topic> findAllByStatusFalse(Pageable pageable);


}
