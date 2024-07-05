package com.forum_hub.forum.domain.topic;

import com.forum_hub.forum.domain.course.CourseRepository;
import com.forum_hub.forum.domain.topic.response.ResponseData;
import com.forum_hub.forum.domain.topic.response.ResponseRepository;
import com.forum_hub.forum.domain.user.UserRepository;
import com.forum_hub.forum.domain.topic.validation.TopicValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository tRepository;
    @Autowired
    private CourseRepository cRepository;
    @Autowired
    private UserRepository uRepository;
    @Autowired
    private ResponseRepository rRepository;
    @Autowired
    private List<TopicValidation> topicValidation;

    public TopicData createNewTopic(CreateNewTopic createNewTopic) {
        topicValidation.forEach(v -> v.validate(createNewTopic));

        var title = createNewTopic.title();
        var message = createNewTopic.message();
        var author = uRepository.getReferenceById(createNewTopic.authorId());
        var course = cRepository.getReferenceById(createNewTopic.courseId());
        var topic = new Topic(title, message, author, course);
        tRepository.save(topic);
        return new TopicData(topic);
    }

    public TopicData updateTopicData(UpdateTopicData updateTopicData) {
        var topic = tRepository.getReferenceById(updateTopicData.id());
        topic.updateTopic(updateTopicData);
        return new TopicData(topic);
    }

    public String deleteTopic(Long id) {
        var topic = tRepository.findById(id);
        if (!topic.isPresent() || id == null) {
            throw new ValidationException("Topic with id: " + id + " doesn't exits");
        }
        rRepository.removeAllByTopic(tRepository.getReferenceById(id));
        tRepository.removeById(id);
        return "Topic and response deleted correct";
    }

    public Page<TopicData> topicList(Pageable pageable) {
        return tRepository.findAll(pageable).map(TopicData::new);
    }

    public TopicResponseData showSelectedTopic(Long id, Pageable pageable) {
        if (id == null || !tRepository.existsById(id)) {
            throw new ValidationException("Topic with that id doesn't exist");
        }
        var topic = tRepository.getReferenceById(id);
        var response = rRepository.findAllByTopic(topic, pageable).map(ResponseData::new);
        return new TopicResponseData(new TopicData(topic), response);
    }

    public Page<TopicData> listSolvedTopics(Pageable pageable) {
        var fixedTopics = tRepository.findAllByStatusTrue(pageable);
        return fixedTopics.map(TopicData::new);
    }

    public Page<TopicData> listUnsolvedTopics(Pageable pageable) {
        var topicsNotFixed = tRepository.findAllByStatusFalse(pageable);
        return topicsNotFixed.map(TopicData::new);
    }
}
