package at.htlleonding;

import at.htlleonding.persistence.*;
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

        //---Publications
        var pub1 = new Publication("Romy im Wunderland", "1900", true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.BOOK,0.99));

        var pub2 = new Publication("Zeno im Wunderland", "1901", false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.AUDIOBOOK,1.99));

        var pub3 = new Publication("Martin im Wunderland", "1902", true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.EBOOK,2.99));

        var pub4 = new Publication("Willi im Wunderland", "1903", false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.NEWSPAPER,3.99));

        var pub5 = new Publication("Yimme im Wunderland", "1904", true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.MAGAZINE,4.99));

        var pub6 = new Publication("Robi im Wunderland", "1905", false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.REFERENCEBOOK,5.99));

        //---Clients
        var client1 = new Client("Peter", "Guckindieluft", "p.guck@gmail.com", "+43 660 1234567");
        var client2 = new Client("Hans", "Rainer", "h.rainer@gmail.com", "+43 650 1234567");
        var client3 = new Client("Rudi", "Painer", "r.painer@maio.com", "+43 630 1234567");

        //---Authors
        var author1 = new Author("Romeo", "Bhuiyan", "+43 664 1234567");
        var author2 = new Author("Zeno", "Paukner", "+44 664 1234567");
        var author3 = new Author("Martin", "Hausleitner", "+45 664 1234567");
        var author4 = new Author("William", "Lau", "+50 664 1234567");
        var author5 = new Author("Yimne", "Raid", "+49 664 1234567");
        var author6 = new Author("Max", "Mustermann", "+49 664 1234567");

        //---Topics
        var topic1 = new Topic("Mathematik");
        var topic2 = new Topic("Chemie");
        var topic3 = new Topic("Physik");
        var topic4 = new Topic("Biologie");
        var topic5 = new Topic("Informatik");
        var topic6 = new Topic("Wirtschaft");


        //---Genres
        var genre1 = new Genre("Roman");
        var genre2 = new Genre("Krimi");
        var genre3 = new Genre("Fantasy");
        var genre4 = new Genre("SciFi");
        var genre5 = new Genre("Horror");
        var genre6 = new Genre("Komödie");

        //--- Publisher
        var publisher1 = new Publisher("Hans Peter Verlag");
        var publisher2 = new Publisher("Veritas");
        var publisher3 = new Publisher("Delta Verlag");

        //---Reservations
        // Create Reservation
        // var reservation1 = new Reservation(, client1);


        //---Languages
        var language1 = new Language("Deutsch");
        var language2 = new Language("Englisch");
        var language3 = new Language("Spanisch");
        var language4 = new Language("Französisch");

        //---Employees
        var employee1 = new Employee("Hans", "Müller", "1202");
        var employee2 = new Employee("Peter", "Mayer", "1203");
        var employee3 = new Employee("Buble", "Holzer", "1204");

        //---Invoice

        //---Sale

        //---Rent





        //---Publications and Authors
        target.add(pub1, author1);
        target.add(pub2, author2);
        target.add(pub3, author3);
        target.add(pub4, author4);
        target.add(pub5, author5);
        target.add(pub6, author6);

        //---Publications and Topics
        target.add(pub1, topic1);
        target.add(pub2, topic2);
        target.add(pub3, topic3);
        target.add(pub4, topic4);
        target.add(pub5, topic5);
        target.add(pub6, topic6);

        //---Publications and Genres
        target.add(pub1, genre1);
        target.add(pub2, genre2);
        target.add(pub3, genre3);
        target.add(pub4, genre4);
        target.add(pub5, genre5);
        target.add(pub6, genre6);

        //---Publications and Publisher
        target.add(pub1, publisher1);
        target.add(pub2, publisher2);
        target.add(pub3, publisher3);
        target.add(pub4, publisher1);
        target.add(pub5, publisher2);
        target.add(pub6, publisher3);

        //---Publications and Language


        //---Employees

        //---Clients



    }



    @TestTransaction
    @Test
    public void createSampleData_getAllAuthors_getSixAuthors(){
        createSampleData();

        target.FlushAndClear();

        var authors = target.getAllAuthors();
        Assertions.assertNotNull(authors);
        Assertions.assertEquals(6, authors.size());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAuthorsByLastName_getBhuiyan() {
        createSampleData();

        target.FlushAndClear();

        var author = target.getAuthorByLastName("Bhuiyan");
        Assertions.assertNotNull(target);
        Assertions.assertEquals("Bhuiyan", author.getLastName());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllPublications_get6Publications(){
        createSampleData();

        target.FlushAndClear();

        var publications = target.getAllPublications();
        Assertions.assertNotNull(publications);
        Assertions.assertEquals(6, publications.size());
    }

    @TestTransaction
    @Test
    public void createSampleData_getPublicationByTitle_getRomyWunderland() {
        createSampleData();

        target.FlushAndClear();

        var publication = target.getPublicationByTitle("Romy im Wunderland");
        Assertions.assertNotNull(target);
        Assertions.assertEquals("Romy im Wunderland", publication.getTitle());
    }
















    @TestTransaction
    @Test
    public void createSampleData_getPublicationsByTopic_get1Publication(){
        createSampleData();

        target.FlushAndClear();

        var publications = target.getPublicationsByTopic("Physik");
        Assertions.assertNotNull(publications);
        Assertions.assertEquals(1, publications.size());
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
