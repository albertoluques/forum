package com.forum_hub.forum.domain.topic.response;

import com.forum_hub.forum.domain.topic.TopicData;
import com.forum_hub.forum.domain.topic.TopicRepository;
import com.forum_hub.forum.domain.topic.TopicStatusResponse;
import com.forum_hub.forum.domain.topic.response.validations.ResponseValidator;
import com.forum_hub.forum.domain.user.UserRepository;
import com.forum_hub.forum.infra.errors.IntegrityValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    @Autowired
    private ResponseRepository repository;
    @Autowired
    private UserRepository uRepository;
    @Autowired
    private TopicRepository tRepository;
    @Autowired
    private List<ResponseValidator> responseValidation;

    public ResponseData createResponse(CreateResponseData data) {
        responseValidation.forEach(v -> v.validate(data));

        var message = data.message();
        var author = uRepository.getReferenceById(data.authorId());
        var topic = tRepository.getReferenceById(data.topicId());
        var response = new Response(message, author, topic);
        repository.save(response);
        return new ResponseData(response);
    }

    public ResponseData editResponses(EditResponseData data) {
        if (data.message() == null) {
            throw new ValidationException("This response it's not found");
        }
        if (!repository.existsById(data.authorId())) {
            throw new IntegrityValidation("The id you input it's not valid");
        }
        var response = repository.getReferenceById(data.authorId());
        response.updateMessage(data.message());
        return new ResponseData(response);
    }

    public TopicStatusResponse solvedManager(Long id) {
        idChecker(id);
        var response = repository.getReferenceById(id);
        var topic = tRepository.getReferenceById(response.getTopic().getId());
        response.setSolved();
        var solved = repository.existsByTopicAndSolved(topic, true);
        System.out.println(solved);
        if ((solved && topic.getStatus() == false) || (!solved && topic.getStatus() == true)) {
            topic.setStatus();
        }
        var result = new TopicStatusResponse(new TopicData(topic), new ResponseData(response));
        return result;
    }

    public void deleteResponses(Long id) {
        idChecker(id);
        var topicResponseId = repository.getReferenceById(id).getTopic().getId();
        var topic = tRepository.getReferenceById(topicResponseId);

        repository.deleteById(id);

        var solvedTopic = repository.existsByTopicAndSolved(topic, true);
        var solvedTopicStatus = topic.getStatus();
        if (solvedTopicStatus && !solvedTopic) {
            topic.setStatus();
        }
    }

    private void idChecker(Long id) {
        if (id == null) {
            throw new ValidationException("You should promp the reponse id");
        }
        if (!repository.existsById(id)) {
            throw new IntegrityValidation("There is not a response with the selected id");
        }
    }
}
