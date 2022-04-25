package at.htlleonding;

import at.htlleonding.DTOs.TopicDTO;
import at.htlleonding.Logic.GenreLogic;
import at.htlleonding.Logic.LibraryLogic;
import at.htlleonding.Logic.SaleLogic;
import at.htlleonding.Logic.TopicLogic;
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

    }
}
