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
        // Create 6 Clients
        var client = new Client("Max", "Mustermann", "Musterstrasse 1", "12345", "Musterstadt", "Musterland", "Musterland", "123456789", "

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
        var reservation1 = new Reservation(, "2020-01-01",);


        //---Languages



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







        target.add(publication, topic);
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
