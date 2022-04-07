package at.htlleonding;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import javax.inject.Inject;

@QuarkusTest
public class PersonTest {
    @Inject
    LibraryService ps;

    @TestTransaction
    @Test
    public void callCreateTwins_getByName_Marry_getMarryObject() {
        ps.createTwins();
        var target = ps.getByFirstName("Marry");
        Assertions.assertNotNull(target);
        Assertions.assertEquals("Marry", target.getFirstName());
    }

    @TestTransaction
    @Test
    public void callCreateTwins_getByName_Harry_getHarryObject() {
        ps.createTwins();
        var target = ps.getByFirstName("Harry");
        Assertions.assertNotNull(target);
        Assertions.assertEquals("Harry", target.getFirstName());
    }
}
