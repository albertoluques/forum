package com.forum_hub.forum.domain.topic.response.validations;

import com.forum_hub.forum.domain.topic.response.CreateResponseData;
import com.forum_hub.forum.domain.user.UserRepository;
import com.forum_hub.forum.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidAuthor implements ResponseValidator{
    @Autowired
    UserRepository userRepository;

    @Override
    public void validate(CreateResponseData data) {
        if (data.authorId() == null || !userRepository.existsById(data.authorId())) {
            throw new IntegrityValidation("Author not found");
        }
    }
}
