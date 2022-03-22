package at.htlleonding;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class LibraryTest {
    @Inject
    LibraryService target;

    @TestTransaction
    @Test
    public void check_createAllMediatypes(){
        target.createAllMediatypes();

        target.entityManager.flush();
        target.entityManager.clear();
        Assertions.assertNotNull(target);
        Assertions.assertEquals("Romeo", target.getByAuthor(1));
    }
/*
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
 */
}
