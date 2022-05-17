package at.htlleonding;

import at.htlleonding.persistence.*;
import at.htlleonding.repository.LibraryRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Date;

@QuarkusTest
public class LibraryTest {
    @Inject
    LibraryRepository target;


    private void createPublicationsWithAuthors(){
        //---Publications
        var pub1 = new Publication("Romy im Wunderland", 1900, true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.BOOK,0.99));

        var pub2 = new Publication("Zeno im Wunderland", 1901, false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.AUDIOBOOK,1.99));

        var pub3 = new Publication("Martin im Wunderland", 1902, true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.EBOOK,2.99));

        var pub4 = new Publication("Willi im Wunderland", 1903, false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.NEWSPAPER,3.99));

        var pub5 = new Publication("Yimme im Wunderland", 1904, true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.MAGAZINE,4.99));

        var pub6 = new Publication("Robi im Wunderland", 1905, false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.REFERENCEBOOK,5.99));

        //---Authors
        var author1 = new Author("Romeo", "Bhuiyan");
        var author2 = new Author("Zeno", "Paukner");
        var author3 = new Author("Martin", "Hausleitner");


        target.add(pub1, author1);
        target.add(pub2, author2);
        target.add(pub3, author3);

        target.add(pub1, author3);
        target.add(pub2, author1);
        target.add(pub3, author2);

        target.add(pub4, author3);
        target.add(pub5, author2);
        target.add(pub6, author1);


    }

    private void createPublicationsWithTopics(){
        //---Publications
        var pub1 = new Publication("Romy im Wunderland", 1900, true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.BOOK,0.99));

        var pub2 = new Publication("Zeno im Wunderland", 1901, false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.AUDIOBOOK,1.99));

        var pub3 = new Publication("Martin im Wunderland", 1902, true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.EBOOK,2.99));

        var pub4 = new Publication("Willi im Wunderland", 1903, false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.NEWSPAPER,3.99));

        var pub5 = new Publication("Yimme im Wunderland", 1904, true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.MAGAZINE,4.99));

        var pub6 = new Publication("Robi im Wunderland", 1905, false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.REFERENCEBOOK,5.99));

        //---Topics
        var topic1 = new Topic("Mathematik");
        var topic2 = new Topic("Chemie");
        var topic3 = new Topic("Physik");
        var topic4 = new Topic("Biologie");
        var topic5 = new Topic("Informatik");
        var topic6 = new Topic("Wirtschaft");

        //---Publications and Topics
        //target.add(pub1, topic1);
        //target.add(pub2, topic2);
        //target.add(pub3, topic3);
        //target.add(pub4, topic4);
        //target.add(pub5, topic5);
        //target.add(pub6, topic6);
    }

    private void createPublicationsAndGenres(){
        //---Publications
        var pub1 = new Publication("Romy im Wunderland", 1900, true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.BOOK,0.99));

        var pub2 = new Publication("Zeno im Wunderland", 1901, false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.AUDIOBOOK,1.99));

        var pub3 = new Publication("Martin im Wunderland", 1902, true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.EBOOK,2.99));

        var pub4 = new Publication("Willi im Wunderland", 1903, false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.NEWSPAPER,3.99));

        var pub5 = new Publication("Yimme im Wunderland", 1904, true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.MAGAZINE,4.99));

        var pub6 = new Publication("Robi im Wunderland", 1905, false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.REFERENCEBOOK,5.99));

        //---Genres
        var genre1 = new Genre("Roman");
        var genre2 = new Genre("Krimi");
        var genre3 = new Genre("Fantasy");
        var genre4 = new Genre("SciFi");
        var genre5 = new Genre("Horror");
        var genre6 = new Genre("Komödie");

        target.add(pub1, genre6);
        target.add(pub2, genre5);
        target.add(pub3, genre4);
        target.add(pub4, genre3);
        target.add(pub5, genre2);
        target.add(pub6, genre1);

        target.add(pub1, genre1);
        target.add(pub2, genre2);
        target.add(pub3, genre3);
        target.add(pub4, genre4);
        target.add(pub5, genre5);
        target.add(pub6, genre6);


    }
    private void createPublicationsAndPublisher(){
        //---Publications
        var pub1 = new Publication("Romy im Wunderland", 1900, true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.BOOK,0.99));

        var pub2 = new Publication("Zeno im Wunderland", 1901, false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.AUDIOBOOK,1.99));

        var pub3 = new Publication("Martin im Wunderland", 1902, true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.EBOOK,2.99));

        var pub4 = new Publication("Willi im Wunderland", 1903, false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.NEWSPAPER,3.99));

        var pub5 = new Publication("Yimme im Wunderland", 1904, true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.MAGAZINE,4.99));

        var pub6 = new Publication("Robi im Wunderland", 1905, false);
        pub1.setMediatype(new Mediatype(MediatypeEnum.REFERENCEBOOK,5.99));

        //--- Publisher
        var publisher1 = new Publisher("Hans Peter Verlag");
        var publisher2 = new Publisher("Veritas");
        var publisher3 = new Publisher("Delta Verlag");

        //---Publications and Publisher
        target.add(pub1, publisher1);
        target.add(pub2, publisher2);
        target.add(pub3, publisher3);
        target.add(pub4, publisher1);
        target.add(pub5, publisher2);
        target.add(pub6, publisher3);
    }

    private void createPublicationsAndLanguages() {

        //---Publications
        var pub1 = new Publication("Romy im Wunderland", 1900, true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.BOOK,0.99));

        var pub2 = new Publication("Zeno im Wunderland", 1901, false);
        pub2.setMediatype(new Mediatype(MediatypeEnum.AUDIOBOOK,1.99));

        var pub3 = new Publication("Martin im Wunderland", 1902, true);
        pub3.setMediatype(new Mediatype(MediatypeEnum.EBOOK,2.99));

        var pub4 = new Publication("Willi im Wunderland", 1903, false);
        pub4.setMediatype(new Mediatype(MediatypeEnum.NEWSPAPER,3.99));

        var pub5 = new Publication("Yimme im Wunderland", 1904, true);
        pub5.setMediatype(new Mediatype(MediatypeEnum.MAGAZINE,4.99));

        var pub6 = new Publication("Robi im Wunderland", 1905, false);
        pub6.setMediatype(new Mediatype(MediatypeEnum.REFERENCEBOOK,5.99));

        //---Languages
        var language1 = new Language("Deutsch");
        var language2 = new Language("Englisch");
        var language3 = new Language("Spanisch");
        var language4 = new Language("Französisch");

        //---Publications and Language
        target.add(pub1, language1);
        target.add(pub2, language2);
        target.add(pub3, language3);
        target.add(pub4, language4);
        target.add(pub5, language1);
        target.add(pub6, language1);
    }


    private void createSampleData() {

        //---Publications
        var pub1 = new Publication("Romy im Wunderland", 1900, true);
        var media1 = new Mediatype(MediatypeEnum.BOOK,0.99);

        var pub2 = new Publication("Zeno im Wunderland", 1901, false);
        var media2 = new Mediatype(MediatypeEnum.AUDIOBOOK,1.99);

        var pub3 = new Publication("Martin im Wunderland", 1902, true);
        var media3 = new Mediatype(MediatypeEnum.EBOOK,2.99);

        var pub4 = new Publication("Willi im Wunderland", 1903, false);
        var media4 = new Mediatype(MediatypeEnum.NEWSPAPER,3.99);

        var pub5 = new Publication("Yimme im Wunderland", 1904, true);
        var media5 = new Mediatype(MediatypeEnum.MAGAZINE,4.99);

        var pub6 = new Publication("Robi im Wunderland", 1905, false);
        var media6 = new Mediatype(MediatypeEnum.REFERENCEBOOK,5.99);

        //---Clients
        var client1 = new Client("Peter", "Guckindieluft", "+43 660 1234567", "p.guck@gmail.com");
        var client2 = new Client("Hans", "Rainer", "+43 650 1234567", "h.rainer@gmail.com");
        var client3 = new Client("Rudi", "Painer", "+43 630 1234567", "r.painer@maio.com");

        //---Authors
        var author1 = new Author("Romeo", "Bhuiyan");
        var author2 = new Author("Zeno", "Paukner");
        var author3 = new Author("Martin", "Hausleitner");
        var author4 = new Author("William", "Lau");
        var author5 = new Author("Yimme", "Raid");
        var author6 = new Author("Max", "Mustermann");

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
        var reservation1 = new Reservation(new Date(2014, 02, 01), client1);
        var reservation2 = new Reservation(new Date(2004, 05, 03), client1);
        var reservation3 = new Reservation(new Date(2017, 11, 02), client2);
        var reservation4 = new Reservation(new Date(2019, 01, 9), client2);
        var reservation5 = new Reservation(new Date(2021, 03, 07), client3);
        var reservation6 = new Reservation(new Date(2009, 10, 8), client3);


        //---Languages
        var language1 = new Language("Deutsch");
        var language2 = new Language("Englisch");
        var language3 = new Language("Spanisch");
        var language4 = new Language("Französisch");

        //---Employees
        var employee1 = new Employee("Hans", "Müller", 1202);
        var employee2 = new Employee("Peter", "Mayer", 1203);
        var employee3 = new Employee("Buble", "Holzer", 1204);

        //---Invoice

        //---Sale

        //---Rent

        //---Publication and Mediatypes
        target.add(pub1, media1);
        target.add(pub2, media2);
        target.add(pub3, media3);
        target.add(pub4, media4);
        target.add(pub5, media5);
        target.add(pub6, media6);

        //---Publications and Authors
        target.add(pub1, author1);
        target.add(pub2, author2);
        target.add(pub3, author3);
        target.add(pub4, author4);
        target.add(pub5, author5);
        target.add(pub6, author6);

        //---Publications and Topics
        //target.add(pub1, topic1);
        //target.add(pub2, topic2);
        //target.add(pub3, topic3);
        //target.add(pub4, topic4);
        //target.add(pub5, topic5);
        //target.add(pub6, topic6);

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
        target.add(pub1, language1);
        target.add(pub2, language2);
        target.add(pub3, language3);
        target.add(pub4, language4);
        target.add(pub5, language1);
        target.add(pub6, language1);

        //---Employees
        target.add(employee1);
        target.add(employee2);
        target.add(employee3);

        //---Clients
        target.add(client1);
        target.add(client2);
        target.add(client3);

        //---Reservations
        target.add(reservation1);
        target.add(reservation2);
        target.add(reservation3);
        target.add(reservation4);
        target.add(reservation5);
        target.add(reservation6);

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
    public void createSampleData_getAllPublications_getSixPublications(){
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
    public void createSampleData_getAllTopics_getSixTopics() {
        createSampleData();

        target.FlushAndClear();

        var topics = target.getAllTopics();
        Assertions.assertNotNull(topics);
        Assertions.assertEquals(6, topics.size());
    }

    @TestTransaction
    @Test
    public void createSampleData_getPublicationsByTopic_getOnePublication(){
        createSampleData();

        target.FlushAndClear();

        var publications = target.getPublicationsByTopic("Physik");
        Assertions.assertNotNull(publications);
        Assertions.assertEquals(1, publications.size());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllGenres_getSixGenres() {
        createSampleData();

        target.FlushAndClear();

        var genres = target.getAllGenres();
        Assertions.assertNotNull(genres);
        Assertions.assertEquals(6, genres.size());
    }

    @TestTransaction
    @Test
    public void createSampleData_getPublicationsByGenre_getOnePublication(){
        createSampleData();

        target.FlushAndClear();

        var publications = target.getPublicationsByGenre("Roman");
        Assertions.assertNotNull(publications);
        Assertions.assertEquals(1, publications.size());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllPublisher_getThreePublisher() {
        createSampleData();

        target.FlushAndClear();

        var publisher = target.getAllPublisher();
        Assertions.assertNotNull(publisher);
        Assertions.assertEquals(3, publisher.size());
    }

    @TestTransaction
    @Test
    public void createSampleData_getPublisherByName_getHans_Peter_Verlag(){
        createSampleData();

        target.FlushAndClear();

        var publisher = target.getPublisherByName("Hans Peter Verlag");
        Assertions.assertNotNull(publisher);
        Assertions.assertEquals("Hans Peter Verlag", publisher.getPublisherName());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllLanguages_getFourLanguages() {
        createSampleData();

        target.FlushAndClear();

        var languages = target.getAllPublisher();
        Assertions.assertNotNull(languages);
        Assertions.assertEquals(3, languages.size());
    }

    @TestTransaction
    @Test
    public void createSampleData_getLanguageByLanguageCode_getOneLanguage() {
        createSampleData();

        target.FlushAndClear();

        var language = target.getLanguageByLanguageCode("Deutsch");
        Assertions.assertNotNull(language);
        Assertions.assertEquals("Deutsch", language.getLanguageCode());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllReservations_getSixReservations() {
        createSampleData();

        target.FlushAndClear();

        var reservations = target.getAllReservations();
        Assertions.assertNotNull(reservations);
        Assertions.assertEquals(6, reservations.size());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllClients_getThreeClients() {
        createSampleData();

        target.FlushAndClear();

        var clients = target.getAllClients();
        Assertions.assertNotNull(clients);
        Assertions.assertEquals(3, clients.size());
    }

    @TestTransaction
    @Test
    public void createSampleData_getAllEmployees_getSixEmployees() {
        createSampleData();

        target.FlushAndClear();

        var employees = target.getAllEmployees();
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(3, employees.size());
    }

   /* @TestTransaction
    @Test
    public void createSampleData_removeOneData_loseOneData() {

        var pub1 = new Publication("Romy im Wunderland", "1900", true);
        pub1.setMediatype(new Mediatype(MediatypeEnum.BOOK,0.99));

        target.add(pub1);
        target.flush();
        var tmpId = pub1.getId();
        target.clear();
        var deleteTarget = target.getPublicationById(tmpId);

        //target.remove(deleteTarget);

        Assertions.assertFalse(target.contains(deleteTarget));
    }*/



}
