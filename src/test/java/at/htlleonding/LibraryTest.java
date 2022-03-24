package at.htlleonding;

import at.htlleonding.persistence.Author;
import at.htlleonding.persistence.Publication;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class LibraryTest {
    @Inject
    LibraryService target;

    private  void createSampleData() {
        var author1 = new Author("Franz", "Ower", "660");
        var author2 = new Author("Georg", "Orwell", "660");

        var publication = new Publication("1984", "1960", true);

        target.add(author1);

        target.add(publication, author2);
    }



    @TestTransaction
    @Test
    public void createSampleData_getAllAuthors_get2Authors(){
        //target.createAllMediatypes();
        createSampleData();

        target.FlushAndClear();

        var authors = target.getAllAuthors();
        Assertions.assertNotNull(authors);
        Assertions.assertEquals(2, authors.size());
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
