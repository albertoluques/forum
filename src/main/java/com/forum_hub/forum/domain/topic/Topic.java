package com.forum_hub.forum.domain.topic;

import com.forum_hub.forum.domain.course.Course;
import com.forum_hub.forum.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @Column(unique = true)
    private String message;
    private LocalDateTime dateTime;
    private Boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_author_id")
    private User topicUser;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    public Topic(String title, String message, User author, Course course) {
        this.title = title;
        this.message = message;
        this.dateTime = LocalDateTime.now();
        this.status = false;
        this.topicUser = author;
        this.course = course;

    }

    public void updateTopic(UpdateTopicData updateTopicData) {
        if (updateTopicData.message() != null) {
            this.message = updateTopicData.message();
        }
        if (updateTopicData.title() != null) {
            this.title = updateTopicData.title();
        }
    }
    public void setStatus(){
        this.status = !status;
    }
}
