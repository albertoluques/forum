package com.forum_hub.forum.domain.topic.validation;

import com.forum_hub.forum.domain.topic.CreateNewTopic;
import com.forum_hub.forum.domain.topic.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicExists implements TopicValidation {
    @Autowired
    private TopicRepository tRepository;

    @Override
    public void validate(CreateNewTopic createNewTopic) {
        var titleExists = tRepository.existsByTitleAndMessage(createNewTopic.title(), createNewTopic.message());

        if (titleExists != null && titleExists) {
            throw new ValidationException("Topic already exist!");
        }
    }
}
