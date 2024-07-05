package com.forum_hub.forum.domain.topic.validation;

import com.forum_hub.forum.domain.topic.CreateNewTopic;

public interface TopicValidation {
    public void validate(CreateNewTopic createNewTopic);
}
