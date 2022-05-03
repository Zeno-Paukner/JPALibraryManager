package at.htlleonding;

import at.htlleonding.DTOsOLD.SaleDTO;
import at.htlleonding.Logic.SaleLogic;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;

@QuarkusTest
public class SaleLogicTest {
    @Inject
    SaleLogic saleLogic;

    @TestTransaction
    @Test
    public void checkIfSaleIsCreated() {
        SaleDTO saleDTO1 = new SaleDTO();
        var SaleID = saleLogic.createSale(saleDTO1);
        Assertions.assertEquals(SaleID, 1);
    }

    @TestTransaction
    @Test
    public void checkIfaSaleCanBeaDuplicate() {
        SaleDTO saleDTO1 = new SaleDTO();
        SaleDTO saleDTO2 = new SaleDTO();
        var saleID = saleLogic.createSale(saleDTO1);
        var saleID2 = saleLogic.createSale(saleDTO2);

        Assertions.assertEquals(saleID, saleID2);
    }

    @TestTransaction
    @Test
    public void checkIfCopyIsAvailableToPurchase() {
        var saleID = saleLogic.checkIfCopyIsAvailableToPurchase(1);
    }
}