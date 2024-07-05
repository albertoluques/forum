package com.forum_hub.forum.domain.topic.response.validations;

import com.forum_hub.forum.domain.topic.response.CreateResponseData;

public interface ResponseValidator {
    public void validate(CreateResponseData data);
}
