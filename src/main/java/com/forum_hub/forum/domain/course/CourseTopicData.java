package com.forum_hub.forum.domain.course;


import com.forum_hub.forum.domain.topic.TopicData;
import org.springframework.data.domain.Page;


public record CourseTopicData(CourseData course, Page<TopicData> topics) {
}
