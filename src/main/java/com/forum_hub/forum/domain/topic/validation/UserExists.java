package com.forum_hub.forum.domain.topic.validation;

import com.forum_hub.forum.domain.topic.CreateNewTopic;
import com.forum_hub.forum.domain.user.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class UserExists implements TopicValidation {
    @Autowired
    private UserRepository uRepository;

    @Override
    public void validate(CreateNewTopic createNewTopic) {
        var user = uRepository.existsById(createNewTopic.authorId());
        if (!user) {
            new ValidationException("Author not found");
        }
    }
}
