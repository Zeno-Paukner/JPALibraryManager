package at.htlleonding;

import at.htlleonding.DTOs.GenreDTO;
import at.htlleonding.DTOs.SaleDTO;
import at.htlleonding.Logic.GenreLogic;
import at.htlleonding.Logic.LibraryLogic;
import at.htlleonding.Logic.SaleLogic;
import at.htlleonding.Logic.TopicLogic;
import at.htlleonding.persistence.Client;
import at.htlleonding.persistence.Copy;
import at.htlleonding.persistence.Genre;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@QuarkusTest
public class SaleLogicTest {
    @Inject
    SaleLogic saleLogic;

    @TestTransaction
    @Test
    public void checkIfaGenreCanBeaDuplicate() {
        GenreDTO genreDTO1 = new GenreDTO("Action");
        GenreDTO genreDTO2 = new GenreDTO("Action");
        var genreID = GenreLogic.createGenre(genreDTO1);
        var genreID2 = GenreLogic.createGenre(genreDTO2);

        Assertions.assertEquals(genreID, genreID2);

        Copy cy1 = new Copy();
        Client ct

        SaleDTO saleDTO = new SaleDTO(c1.getId(), );

        saleLogic.createSale(saleDTO);
    }
}
