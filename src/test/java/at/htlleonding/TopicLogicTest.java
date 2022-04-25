package at.htlleonding;

import at.htlleonding.DTOs.GenreDTO;
import at.htlleonding.DTOs.TopicDTO;
import at.htlleonding.Logic.GenreLogic;
import at.htlleonding.Logic.LibraryLogic;
import at.htlleonding.Logic.SaleLogic;
import at.htlleonding.Logic.TopicLogic;
import io.quarkus.test.TestTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TopicLogicTest {
    @Inject
    TopicLogic topicLogic;
    GenreLogic genreLogic;
    SaleLogic saleLogic;
    LibraryLogic libraryLogic;
    EntityManager entityManager;

    @TestTransaction
    @Test

    public void checkIfaGenreCanBeaDuplicate() {
        TopicDTO topicDTO1 = new TopicDTO("LiveLove");
        TopicDTO topicDTO2 = new TopicDTO("LiveLove");
        var topicID = topicLogic.createTopic(topicDTO1);
        var topicID2 = topicLogic.createTopic(topicDTO2);

        Assertions.assertEquals(topicDTO2, topicDTO1);
    }

}
