package at.htlleonding.Logic;

import at.htlleonding.DTOs.TopicDTO;
import at.htlleonding.persistence.Topic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class TopicLogic {

    @Inject
    EntityManager entityManager;

    @Transactional
    public Topic createTopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        //check every if the topic name is already in the database
        for (Topic topic1 : entityManager.createQuery("SELECT t FROM Topic t", Topic.class).getResultList()) {
            if (topic1.getKeyword().equals(topicDTO.getKeyword())) {
                return topic1;
            }
        }

        topic.setKeyword(topicDTO.getKeyword());
        entityManager.persist(topic);

        return topic;
    }
}
