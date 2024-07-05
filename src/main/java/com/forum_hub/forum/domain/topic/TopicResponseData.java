package com.forum_hub.forum.domain.topic;

import com.forum_hub.forum.domain.topic.response.ResponseData;
import org.springframework.data.domain.Page;

public record TopicResponseData(TopicData topicData, Page<ResponseData> response) {
}
