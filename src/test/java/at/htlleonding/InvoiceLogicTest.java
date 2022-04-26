package at.htlleonding;

import at.htlleonding.DTOs.InvoiceDTO;
import at.htlleonding.DTOs.TopicDTO;
import at.htlleonding.Logic.InvoiceLogic;
import at.htlleonding.persistence.Client;
import at.htlleonding.persistence.Employee;
import at.htlleonding.repository.LibraryRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class InvoiceLogicTest {
    @Inject
    InvoiceLogic target;

    @TestTransaction
    @Test
    public void checkIfaGenreCanBeaDuplicate() {
        var client1 = new Client("Peter", "Guckindieluft", "p.guck@gmail.com", "+43 660 1234567");
        var client2 = new Client("Hans", "Rainer", "h.rainer@gmail.com", "+43 650 1234567");
        var client3 = new Client("Rudi", "Painer", "r.painer@maio.com", "+43 630 1234567");

        var employee1 = new Employee("Hans", "Müller", 1202);
        var employee2 = new Employee("Peter", "Mayer", 1203);
        var employee3 = new Employee("Buble", "Holzer", 1204);


        InvoiceDTO invoice1 = new InvoiceDTO();

        target.createInvoice(invoice1);
    }
}
