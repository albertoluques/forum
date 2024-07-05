package com.forum_hub.forum.domain.topic.response.validations;

import com.forum_hub.forum.domain.topic.TopicRepository;
import com.forum_hub.forum.domain.topic.response.CreateResponseData;
import com.forum_hub.forum.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidTopic implements ResponseValidator{

    @Autowired
    TopicRepository tRepository;

    @Override
    public void validate(CreateResponseData data) {
        if (data.topicId() == null || !tRepository.existsById(data.topicId())) {
            throw new IntegrityValidation("Topic not found");
        }
    }
}
