package at.htlleonding;

import at.htlleonding.DTOs.GenreDTO;
import at.htlleonding.Logic.GenreLogic;
import at.htlleonding.Logic.LibraryLogic;
import at.htlleonding.Logic.SaleLogic;
import at.htlleonding.Logic.TopicLogic;
import at.htlleonding.persistence.Genre;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class GenreLogicTest {
    @Inject
    TopicLogic topicLogic;
    GenreLogic genreLogic;
    SaleLogic saleLogic;
    LibraryLogic libraryLogic;

    @TestTransaction
    @Test

    public void checkIfaGenreCanBeaDuplicate() {
        GenreDTO genreDTO1 = new GenreDTO("Action");
        GenreDTO genreDTO2 = new GenreDTO("Action");
        var genreID = genreLogic.createGenre(genreDTO1);
        var genreID2 = genreLogic.createGenre(genreDTO2);

        Assertions.assertEquals(genreID, genreID2);
    }

    @TestTransaction
    @Test

    public void checkGenresPublications() {
        Genre genreDTO1 = new Genre("Action");
        var publication= genreDTO1.getPublications();


        Assertions.assertNotNull(publication, "Publication Exists");
    }

}
