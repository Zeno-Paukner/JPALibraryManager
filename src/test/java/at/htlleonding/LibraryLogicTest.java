package at.htlleonding;

import at.htlleonding.DTOs.*;
import at.htlleonding.Logic.*;
import at.htlleonding.Logic.SaleLogic;
import at.htlleonding.Logic.TopicLogic;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class LibraryLogicTest {

    @Inject
    TopicLogic topicLogic;
    GenreLogic genreLogic;
    SaleLogic saleLogic;
    LibraryLogic libraryLogic;

    @TestTransaction
    @Test
    public void checkIfaTopicCanBeaDuplicate() {
        TopicDTO topicDTO1 = new TopicDTO("Lego");
        TopicDTO topicDTO2 = new TopicDTO("Lego");
        var topicID = topicLogic.createTopic(topicDTO1);
        var topicID2 = topicLogic.createTopic(topicDTO2);

        Assertions.assertEquals(topicID, topicID2);
    }
    //test GenreLogic
    @TestTransaction
    @Test

    public void checkIfaGenreCanBeaDuplicate() {
        GenreDTO genreDTO1 = new GenreDTO("Action");
        GenreDTO genreDTO2 = new GenreDTO("Action");
        var genreID = genreLogic.createGenre(genreDTO1);
        var genreID2 = genreLogic.createGenre(genreDTO2);

        Assertions.assertEquals(genreID, genreID2);
    }

    //A Test for SaleLogic
    @TestTransaction
    @Test
    public void checkIfallCopiesofSaleAreAvavile() {
        ClientDTO clientDTO = new ClientDTO("Max", "Mustermann", "123456789", "max@mustermann.at");
        EmployeeDTO employeeDTO = new EmployeeDTO("Max", "Mustermann", 1234);

    }
}
