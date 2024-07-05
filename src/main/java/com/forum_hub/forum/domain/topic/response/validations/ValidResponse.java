package com.forum_hub.forum.domain.topic.response.validations;

import com.forum_hub.forum.domain.topic.TopicRepository;
import com.forum_hub.forum.domain.topic.response.CreateResponseData;
import com.forum_hub.forum.domain.topic.response.ResponseRepository;
import com.forum_hub.forum.domain.user.UserRepository;
import com.forum_hub.forum.infra.errors.IntegrityValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidResponse implements ResponseValidator {

    @Autowired
    ResponseRepository rRepository;
    @Autowired
    UserRepository uRepository;
    @Autowired
    TopicRepository tRepository;

    @Override
    public void validate(CreateResponseData data) {
        if (data.message() == null) {
            throw new IntegrityValidation("Message not found");
        }
        if(rRepository.existsByTopicAndMessageAndUserResponse(
                tRepository.getReferenceById(data.topicId()),
                data.message(), uRepository.getReferenceById(data.authorId()))
        ) {
            throw new ValidationException("This response it's already in the topic");
        }
    }
}
