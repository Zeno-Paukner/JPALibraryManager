package at.htlleonding;

import at.htlleonding.DTOs.CopyDTO;
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
    public void CheckDateBeforeSale() {
        var copyDto= new CopyDTO();
       var copyid = LibraryLogic.createCopy();

    }
}
