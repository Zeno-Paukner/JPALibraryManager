package at.htlleonding;

import at.htlleonding.DTOs.MediatypeDTO;
import at.htlleonding.DTOs.TopicDTO;
import at.htlleonding.Logic.*;
import io.quarkus.test.TestTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

public class MediaTypeLogicTest {
    @Inject
    TopicLogic topicLogic;
    GenreLogic genreLogic;
    SaleLogic saleLogic;
    LibraryLogic libraryLogic;

    @TestTransaction
    @Test
    public void CheckIfTheWritingIsRight() {
        MediatypeDTO mediatypeDTO= new MediatypeDTO("Book");
        var mediaTypeid = MediatypeLogic.createMediatype(mediatypeDTO);
        var topicID2 = topicLogic.createTopic(topicDTO2);

        Assertions.assertEquals(topicID, topicID2);
    }
}
