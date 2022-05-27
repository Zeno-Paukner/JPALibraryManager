package at.htlleonding;
import at.htlleonding.DTOs.*;
import at.htlleonding.Logic.*;
//import at.htlleonding.persistence.LibraryMgmtRepository;
import at.htlleonding.persistence.*;
import at.htlleonding.repository.LibraryRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static at.htlleonding.persistence.MediatypeEnum.*;

@QuarkusTest
public class LibraryMgmtLogicTest {
    @Inject
    EntityManager entityManager;
    @Inject
    LibraryRepository target;
    @Inject
    LibraryLogic libTarget;
    @Inject
    PublicationLogic pubTarget;
    @Inject
    GenreLogic genreTarget;
    @Inject
    TopicLogic topicTarget;
    @Inject
    RentLogic rentTarget;
    @Inject
    ClientLogic clientTarget;
    @Inject
    MediatypeLogic mediaTarget;
    @Inject
    LanguageLogic languageTarget;
    @Inject
    SaleLogic saleTarget;
    @Inject
    InvoiceLogic invoiceTarget;

    /*
    Add rentable items to the library, of each media type, with multiple authors and attributes.
    Verify that these items can be rented.
    */
    @Test
    @TestTransaction
    public void addPaperBookWithOneAuthor_makeRentable_canBeRented()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeBOOK = new MediatypeDTO(BOOK, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeBOOK, publisherIrgendwas);
        pub1.getAuthors().add(author1);

        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");
        //var rent1 = new RentDTO(copy1, employee1, client1);

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeBOOK);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck = libTarget.createCopy(copy1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(false, copyCheck.getRented());
        Assertions.assertEquals(mediatypeBOOK.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());

    }

    @Test
    @TestTransaction
    public void addPaperBookWithThreeAuthors_makeRentable_canBeRented()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeBOOK = new MediatypeDTO(BOOK, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var author2 = new AuthorDTO("Zeno", "Paukner");
        var author3 = new AuthorDTO("Martin", "Hausleitner");

        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeBOOK, publisherIrgendwas);
        pub1.getAuthors().add(author1);
        pub1.getAuthors().add(author2);
        pub1.getAuthors().add(author3);

        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");
        //var rent1 = new RentDTO(copy1, employee1, client1);

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeBOOK);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck = libTarget.createCopy(copy1);
        var aut1 =libTarget.createAuthor(author1);
        var aut2 = libTarget.createAuthor(author2);
        var aut3 = libTarget.createAuthor(author3);

        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);
        publication.getAuthors().add(aut1);
        publication.getAuthors().add(aut2);
        publication.getAuthors().add(aut3);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(false, copyCheck.getRented());
        Assertions.assertEquals(3, publication.getAuthors().size());
        Assertions.assertEquals(mediatypeBOOK.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    @Test
    @TestTransaction
    public void addThreeCopiesOfPaperBookWithThreeAuthors_makeRentable_canBeRented()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeBOOK = new MediatypeDTO(BOOK, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var author2 = new AuthorDTO("Zeno", "Paukner");
        var author3 = new AuthorDTO("Martin", "Hausleitner");

        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeBOOK, publisherIrgendwas);
        var pub2 = new PublicationDTO("Zeno im Wunderland",1901, true, languageEN, genreFantasy, mediatypeBOOK, publisherIrgendwas);
        var pub3 = new PublicationDTO("Martin im Wunderland",1902, true, languageEN, genreFantasy, mediatypeBOOK, publisherIrgendwas);

        pub1.getAuthors().add(author1);
        pub1.getAuthors().add(author2);
        pub1.getAuthors().add(author3);
        pub2.getAuthors().add(author1);
        pub2.getAuthors().add(author2);
        pub2.getAuthors().add(author3);
        pub3.getAuthors().add(author1);
        pub3.getAuthors().add(author2);
        pub3.getAuthors().add(author3);

        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var copy2 = new CopyDTO(pub2, (RentDTO) null, date);
        var copy3 = new CopyDTO(pub3, (RentDTO) null, date);

        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");
        //var rent1 = new RentDTO(copy1, employee1, client1);

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeBOOK);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck1 = libTarget.createCopy(copy1);
        var copyCheck2 = libTarget.createCopy(copy2);
        var copyCheck3 = libTarget.createCopy(copy3);

        var aut1 =libTarget.createAuthor(author1);
        var aut2 = libTarget.createAuthor(author2);
        var aut3 = libTarget.createAuthor(author3);

        libTarget.createClient(client1);
        var publication1 = pubTarget.createPublication(pub1);
        publication1.getAuthors().add(aut1);
        publication1.getAuthors().add(aut2);
        publication1.getAuthors().add(aut3);

        var publication2 = pubTarget.createPublication(pub1);
        publication2.getAuthors().add(aut1);
        publication2.getAuthors().add(aut2);
        publication2.getAuthors().add(aut3);

        var publication3 = pubTarget.createPublication(pub1);
        publication3.getAuthors().add(aut1);
        publication3.getAuthors().add(aut2);
        publication3.getAuthors().add(aut3);

        Assertions.assertNotNull(publication1);
        Assertions.assertNotNull(publication2);
        Assertions.assertNotNull(publication3);

        Assertions.assertEquals(false, copyCheck1.getRented());
        Assertions.assertEquals(false, copyCheck2.getRented());
        Assertions.assertEquals(false, copyCheck3.getRented());

        Assertions.assertEquals(3, publication1.getAuthors().size());
        Assertions.assertEquals(3, publication2.getAuthors().size());
        Assertions.assertEquals(3, publication3.getAuthors().size());

        Assertions.assertEquals(mediatypeBOOK.getMediatypeEnum(), publication1.getMediatype().getMediatypeEnum());
        Assertions.assertEquals(mediatypeBOOK.getMediatypeEnum(), publication2.getMediatype().getMediatypeEnum());
        Assertions.assertEquals(mediatypeBOOK.getMediatypeEnum(), publication3.getMediatype().getMediatypeEnum());

    }

    @Test
    @TestTransaction
    public void addNewspaperWithOneAuthor_makeRentable_canBeRented()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeNEWSPAPER = new MediatypeDTO(NEWSPAPER, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeNEWSPAPER, publisherIrgendwas);
        pub1.getAuthors().add(author1);
        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");
        //var rent1 = new RentDTO(copy1, employee1, client1);

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeNEWSPAPER);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck = libTarget.createCopy(copy1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(false, copyCheck.getRented());

        Assertions.assertEquals(mediatypeNEWSPAPER.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());

    }

    @Test
    @TestTransaction
    public void addAudioBookWithOneAuthor_makeRentable_canBeRented()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeAUDIOBOOK = new MediatypeDTO(AUDIOBOOK, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeAUDIOBOOK, publisherIrgendwas);
        pub1.getAuthors().add(author1);

        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");
        //var rent1 = new RentDTO(copy1, employee1, client1);

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeAUDIOBOOK);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck = libTarget.createCopy(copy1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(false, copyCheck.getRented());
        Assertions.assertEquals(mediatypeAUDIOBOOK.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());

    }

    @Test
    @TestTransaction
    public void addEBookWithOneAuthor_makeRentable_canBeRented()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeEBOOK = new MediatypeDTO(EBOOK, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeEBOOK, publisherIrgendwas);
        pub1.getAuthors().add(author1);

        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");
        var rent1 = new RentDTO(copy1, employee1, client1);

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeEBOOK);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck = libTarget.createCopy(copy1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(false, copyCheck.getRented());
        Assertions.assertEquals(mediatypeEBOOK.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    @Test
    @TestTransaction
    public void addJournalWithOneAuthor_makeRentable_canBeRented()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        pub1.getAuthors().add(author1);


        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");
        //var rent1 = new RentDTO(copy1, employee1, client1);

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck = libTarget.createCopy(copy1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(false, copyCheck.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    /*
     Add a library customer.
     Add a library employee.
     */
    @Test
    @TestTransaction
    public void addLibraryCustomer_isAvailable()
    {
        ClientDTO client1 = new ClientDTO("Romeo", "Bhuiyan", "3242903412303", "romeobhuiyan@gmail.com");

        var clientPersisted = libTarget.createClient(client1);

        Assertions.assertNotNull(clientPersisted);
        Assertions.assertEquals("Romeo", clientPersisted.getFirstName());
        Assertions.assertEquals("Bhuiyan", clientPersisted.getLastName());
        Assertions.assertEquals("romeobhuiyan@gmail.com", clientPersisted.getEmail());
        Assertions.assertEquals("3242903412303", clientPersisted.getPhoneNumber());

    }

    @Test
    @TestTransaction
    public void addLibraryEmployee_isAvailable()
    {
        EmployeeDTO employee1 = new EmployeeDTO("Romeo", "Bhuiyan", 2900);

        var employeePersisted = libTarget.createEmployee(employee1);

        Assertions.assertNotNull(employeePersisted);
        Assertions.assertEquals("Romeo", employeePersisted.getFirstName());
        Assertions.assertEquals("Bhuiyan", employeePersisted.getLastName());
        Assertions.assertEquals(2900, employeePersisted.getSalary());
    }

    /*
     Rent out, bring back, reserve and prolong.
     Verify state of rented items and customer's rent list.
    */
    @Test
    @TestTransaction
    public void customerRentsRentableItem_ItemIsRented()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        pub1.getAuthors().add(author1);


        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");


        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck = libTarget.createCopy(copy1);
        copy1 = new CopyDTO(copy1.getPublication(), copy1.getSale(), copy1.getDateOfPurchase(), copyCheck.getId());
        var rent1 = new RentDTO(copy1, employee1, client1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);
        rentTarget.rentCopy(rent1);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(true, copyCheck.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    @Test
    @TestTransaction
    public void customerRentsOneOfThreeCopiesOfRentableItem_TwoRentableItemsRemain_CustomerHasRent()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        pub1.getAuthors().add(author1);


        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var copy2 = new CopyDTO(pub1, (RentDTO) null, date);
        var copy3 = new CopyDTO(pub1, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck1 = libTarget.createCopy(copy1);
        var copyCheck2 = libTarget.createCopy(copy2);
        var copyCheck3 = libTarget.createCopy(copy3);
        copy1 = new CopyDTO(copy1.getPublication(), copy1.getSale(), copy1.getDateOfPurchase(), copyCheck1.getId());
        var rent1 = new RentDTO(copy1, employee1, client1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);
        rentTarget.rentCopy(rent1);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(true, copyCheck1.getRented());
        Assertions.assertEquals(false, copyCheck2.getRented());
        Assertions.assertEquals(false, copyCheck3.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    @Test
    @TestTransaction
    public void customerRentsThreeOfThreeCopiesOfRentableItem_TryRentAnother_RentNotPossible()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        pub1.getAuthors().add(author1);


        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var copy2 = new CopyDTO(pub1, (RentDTO) null, date);
        var copy3 = new CopyDTO(pub1, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck1 = libTarget.createCopy(copy1);
        var copyCheck2 = libTarget.createCopy(copy2);
        var copyCheck3 = libTarget.createCopy(copy3);
        copy1 = new CopyDTO(copy1.getPublication(), copy1.getSale(), copy1.getDateOfPurchase(), copyCheck1.getId());
        copy2 = new CopyDTO(copy2.getPublication(), copy2.getSale(), copy2.getDateOfPurchase(), copyCheck2.getId());
        copy3 = new CopyDTO(copy3.getPublication(), copy3.getSale(), copy3.getDateOfPurchase(), copyCheck3.getId());
        var rent1 = new RentDTO(copy1, employee1, client1);
        var rent2 = new RentDTO(copy2, employee1, client1);
        var rent3 = new RentDTO(copy3, employee1, client1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);
        rentTarget.rentCopy(rent1);
        rentTarget.rentCopy(rent2);
        rentTarget.rentCopy(rent3);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(true, copyCheck1.getRented());
        Assertions.assertEquals(true, copyCheck2.getRented());
        Assertions.assertEquals(true, copyCheck3.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());

        try {
            rentTarget.rentCopy(rent3);
        }
        catch (RuntimeException e){
            Assertions.assertEquals(e.getMessage(), "Das Buch ist bereits ausgeliehen");
        }
    }

    @Test
    @TestTransaction
    public void customerOneItemOfEachMediaType_ItemsAreRented()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var mediatypeEBOOK = new MediatypeDTO(EBOOK, 0.99);
        var mediatypeBOOK = new MediatypeDTO(BOOK, 0.99);
        var mediatypeAUDIOBOOK = new MediatypeDTO(AUDIOBOOK, 0.99);
        var mediatypeREFERENCEBOOK = new MediatypeDTO(REFERENCEBOOK, 0.99);
        var mediatypeNEWSPAPER = new MediatypeDTO(NEWSPAPER, 0.99);
        var mediatypeMAGAZINE = new MediatypeDTO(MAGAZINE, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        var pub2 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeEBOOK, publisherIrgendwas);
        var pub3 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeBOOK, publisherIrgendwas);
        var pub4 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeAUDIOBOOK, publisherIrgendwas);
        var pub5 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeREFERENCEBOOK, publisherIrgendwas);
        var pub6 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeNEWSPAPER, publisherIrgendwas);
        var pub7 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeMAGAZINE, publisherIrgendwas);
        pub1.getAuthors().add(author1);
        pub2.getAuthors().add(author1);
        pub3.getAuthors().add(author1);
        pub4.getAuthors().add(author1);
        pub5.getAuthors().add(author1);
        pub6.getAuthors().add(author1);
        pub7.getAuthors().add(author1);


        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var copy2 = new CopyDTO(pub2, (RentDTO) null, date);
        var copy3 = new CopyDTO(pub3, (RentDTO) null, date);
        var copy4 = new CopyDTO(pub4, (RentDTO) null, date);
        var copy5 = new CopyDTO(pub5, (RentDTO) null, date);
        var copy6 = new CopyDTO(pub6, (RentDTO) null, date);
        var copy7 = new CopyDTO(pub7, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck1 = libTarget.createCopy(copy1);
        var copyCheck2 = libTarget.createCopy(copy2);
        var copyCheck3 = libTarget.createCopy(copy3);
        var copyCheck4 = libTarget.createCopy(copy4);
        var copyCheck5 = libTarget.createCopy(copy5);
        var copyCheck6 = libTarget.createCopy(copy6);
        var copyCheck7 = libTarget.createCopy(copy7);
        copy1 = new CopyDTO(copy1.getPublication(), copy1.getSale(), copy1.getDateOfPurchase(), copyCheck1.getId());
        copy2 = new CopyDTO(copy2.getPublication(), copy2.getSale(), copy2.getDateOfPurchase(), copyCheck2.getId());
        copy3 = new CopyDTO(copy3.getPublication(), copy3.getSale(), copy3.getDateOfPurchase(), copyCheck3.getId());
        copy4 = new CopyDTO(copy4.getPublication(), copy4.getSale(), copy4.getDateOfPurchase(), copyCheck4.getId());
        copy5 = new CopyDTO(copy5.getPublication(), copy5.getSale(), copy5.getDateOfPurchase(), copyCheck5.getId());
        copy6 = new CopyDTO(copy6.getPublication(), copy6.getSale(), copy6.getDateOfPurchase(), copyCheck6.getId());
        copy7 = new CopyDTO(copy7.getPublication(), copy7.getSale(), copy7.getDateOfPurchase(), copyCheck7.getId());
        var rent1 = new RentDTO(copy1, employee1, client1);
        var rent2 = new RentDTO(copy2, employee1, client1);
        var rent3 = new RentDTO(copy3, employee1, client1);
        var rent4 = new RentDTO(copy4, employee1, client1);
        var rent5 = new RentDTO(copy5, employee1, client1);
        var rent6 = new RentDTO(copy6, employee1, client1);
        var rent7 = new RentDTO(copy7, employee1, client1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);
        rentTarget.rentCopy(rent1);
        rentTarget.rentCopy(rent2);
        rentTarget.rentCopy(rent3);
        rentTarget.rentCopy(rent4);
        rentTarget.rentCopy(rent5);
        rentTarget.rentCopy(rent6);
        rentTarget.rentCopy(rent7);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(true, copyCheck1.getRented());
        Assertions.assertEquals(true, copyCheck2.getRented());
        Assertions.assertEquals(true, copyCheck3.getRented());
        Assertions.assertEquals(true, copyCheck4.getRented());
        Assertions.assertEquals(true, copyCheck5.getRented());
        Assertions.assertEquals(true, copyCheck6.getRented());
        Assertions.assertEquals(true, copyCheck7.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    @Test
    @TestTransaction
    public void setItemForSale_customerTriesToRent_RentNotPossible()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        pub1.getAuthors().add(author1);

        var copy1 = new CopyDTO(pub1, (SaleDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        var employee = libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck1 = libTarget.createCopy(copy1);
        employee.setCopyAsForSale(copyCheck1);
        copy1 = new CopyDTO(copy1.getPublication(), copy1.getSale(), copy1.getDateOfPurchase(), copyCheck1.getId());
        var rent1 = new RentDTO(copy1, employee1, client1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);

        try {
            rentTarget.rentCopy(rent1);
        }
        catch (RuntimeException e){
            Assertions.assertEquals(e.getMessage(), "Das Buch ist zum Verkauf");
        }

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(false, copyCheck1.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    @Test
    @TestTransaction
    public void setItemForOnDisplay_customerTriesToRent_RentNotPossible()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        pub1.getAuthors().add(author1);

        var copy1 = new CopyDTO(pub1, (SaleDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        var employee = libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck1 = libTarget.createCopy(copy1);
        copyCheck1.setOnDisplay(true);
        copy1 = new CopyDTO(copy1.getPublication(), copy1.getSale(), copy1.getDateOfPurchase(), copyCheck1.getId());
        var rent1 = new RentDTO(copy1, employee1, client1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);

        try {
            rentTarget.rentCopy(rent1);
        }
        catch (RuntimeException e){
            Assertions.assertEquals(e.getMessage(), "Das Buch ist ausgestellt");
        }

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(false, copyCheck1.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    @Test
    @TestTransaction
    public void customerRentsSingleAvailableItem_RentNotPossible_BringsBackItem_RentPossible()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        pub1.getAuthors().add(author1);


        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");


        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck = libTarget.createCopy(copy1);
        copy1 = new CopyDTO(copy1.getPublication(), copy1.getSale(), copy1.getDateOfPurchase(), copyCheck.getId());
        var rent1 = new RentDTO(copy1, employee1, client1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);
        var rentCheck = rentTarget.rentCopy(rent1);
        rent1 = new RentDTO(rent1.getCopy(), rent1.getEmployee(), rent1.getClient(), rentCheck.getId());

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(true, copyCheck.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());


        try {
            rentTarget.rentCopy(rent1);
        }
        catch (RuntimeException e){
            Assertions.assertEquals(e.getMessage(), "Das Buch ist bereits ausgeliehen");
        }

        rentTarget.returnCopy(rent1);
        rentTarget.rentCopy(rent1);
        Assertions.assertNotNull(publication);
        Assertions.assertEquals(true, copyCheck.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    @Test
    @TestTransaction
    public void rentOutItemToCustomerA_customerBmakesReservation_CustomerAreturnsItem_RentPossibleOnlyForCustomerB()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        pub1.getAuthors().add(author1);


        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var clientA = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");
        var clientB = new ClientDTO("Mattias", "Hausleitner", "+43 03123923912", "martin@waffal.at");


        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck = libTarget.createCopy(copy1);
        copy1 = new CopyDTO(copy1.getPublication(), copy1.getSale(), copy1.getDateOfPurchase(), copyCheck.getId());
        var rent1 = new RentDTO(copy1, employee1, clientA);
        var rent2 = new RentDTO(copy1, employee1, clientB);
        libTarget.createAuthor(author1);
        libTarget.createClient(clientA);
        libTarget.createClient(clientB);
        var publication = pubTarget.createPublication(pub1);
        var rentCheck1 = rentTarget.rentCopy(rent1);
        rent1 = new RentDTO(rent1.getCopy(), rent1.getEmployee(), rent1.getClient(), rentCheck1.getId());

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(true, copyCheck.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());

        rentTarget.reserveCopy(rent2);

        rentTarget.returnCopy(rent1);
        try {
            rentTarget.rentCopy(rent1);
        }
        catch (RuntimeException e){
            Assertions.assertEquals("Das Buch ist reserviert", e.getMessage());
        }

        rentTarget.rentCopy(rent2);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(true, copyCheck.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    @Test
    @TestTransaction
    public void customerRentsItem_prolongsRentThreeTimes_customerCanOnlyProlongTwoTimes_rentalEndDate6weeksAhead()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        pub1.getAuthors().add(author1);


        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");


        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck = libTarget.createCopy(copy1);
        copy1 = new CopyDTO(copy1.getPublication(), copy1.getSale(), copy1.getDateOfPurchase(), copyCheck.getId());
        var rent1 = new RentDTO(copy1, employee1, client1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);
        var rentCheck = rentTarget.rentCopy(rent1);
        rent1 = new RentDTO(rent1.getCopy(), rent1.getEmployee(), rent1.getClient(), rentCheck.getId());
        var previousDeadLine = rentCheck.getDeadline().getTime();
        rentTarget.prolongRent(rent1);
        rentTarget.prolongRent(rent1);

        try {
            rentTarget.prolongRent(rent1);
        } catch (Exception e){
            Assertions.assertEquals("Muss von Mitarbeiter verlängert werden", e.getMessage());
        }

        var newDeadLine = rentCheck.getDeadline().getTime();
        // divided by 10, otherwise too big for int
        Assertions.assertEquals(241920000, (newDeadLine - previousDeadLine) / 10);
        Assertions.assertNotNull(publication);
        Assertions.assertEquals(true, copyCheck.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    @Test
    @TestTransaction
    public void customerRentsItem_prolongsRentTwoTimes_EmployeeProlongsOneTime_rentalEndDate8weeksAhead()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        pub1.getAuthors().add(author1);


        var copy1 = new CopyDTO(pub1, (RentDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");


        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        var checkEmployee = libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck = libTarget.createCopy(copy1);
        copy1 = new CopyDTO(copy1.getPublication(), copy1.getSale(), copy1.getDateOfPurchase(), copyCheck.getId());
        var rent1 = new RentDTO(copy1, employee1, client1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);
        var rentCheck = rentTarget.rentCopy(rent1);
        rent1 = new RentDTO(rent1.getCopy(), rent1.getEmployee(), rent1.getClient(), rentCheck.getId());
        var previousDeadLine = rentCheck.getDeadline().getTime();
        rentTarget.prolongRent(rent1);
        rentTarget.prolongRent(rent1);

        rentTarget.prolongRent(rent1, checkEmployee);

        var newDeadLine = rentCheck.getDeadline().getTime();
        Assertions.assertEquals(362880000, (newDeadLine - previousDeadLine) / 10);
        Assertions.assertNotNull(publication);
        Assertions.assertEquals(true, copyCheck.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    /*
      - Declare a library item to be for sale, it cannot be rented anymore.
      - Sell one library item to a customer, create invoice. Item cannot be rented anymore.
      - Sell some items of multiple books.
     */
    @Test
    @TestTransaction
    public void setItemForSale_cannotBeRented()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var mediatypeEBOOK = new MediatypeDTO(EBOOK, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        var pub2 = new PublicationDTO("Romy ausm Wunderland",900, false, languageEN, genreFantasy, mediatypeEBOOK, publisherIrgendwas);
        pub1.getAuthors().add(author1);
        pub2.getAuthors().add(author1);

        var copy1 = new CopyDTO(pub1, (SaleDTO) null, date);
        var copy2 = new CopyDTO(pub1, (SaleDTO) null, date);
        var copy3 = new CopyDTO(pub1, (SaleDTO) null, date);
        var copy4 = new CopyDTO(pub2, (SaleDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        var employee = libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck1 = libTarget.createCopy(copy1);
        var copyCheck2 = libTarget.createCopy(copy2);
        var copyCheck3 = libTarget.createCopy(copy3);
        var copyCheck4 = libTarget.createCopy(copy4);
        employee.setCopyAsForSale(copyCheck1);
        employee.setCopyAsForSale(copyCheck2);
        employee.setCopyAsForSale(copyCheck3);
        employee.setCopyAsForSale(copyCheck4);
        copy1 = new CopyDTO(copy1.getPublication(), copy1.getSale(), copy1.getDateOfPurchase(), copyCheck1.getId());
        copy2 = new CopyDTO(copy2.getPublication(), copy2.getSale(), copy2.getDateOfPurchase(), copyCheck2.getId());
        copy3 = new CopyDTO(copy3.getPublication(), copy3.getSale(), copy3.getDateOfPurchase(), copyCheck3.getId());
        copy4 = new CopyDTO(copy4.getPublication(), copy4.getSale(), copy4.getDateOfPurchase(), copyCheck4.getId());
        var rent1 = new RentDTO(copy1, employee1, client1);
        var rent2 = new RentDTO(copy2, employee1, client1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);

        try {
            rentTarget.rentCopy(rent1);
        }
        catch (RuntimeException e){
            Assertions.assertEquals(e.getMessage(), "Das Buch ist zum Verkauf");
        }

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(false, copyCheck1.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());

        SaleDTO saleDTO = new SaleDTO(client1, employee1);
        saleDTO.getCopyList().add(copy2);
        var saleCheck = saleTarget.createSale(saleDTO);
        saleDTO = new SaleDTO(saleDTO.getClient(), saleDTO.getEmployee(), saleCheck.getId());
        try {
            rentTarget.rentCopy(rent1);
        }
        catch (RuntimeException e){
            Assertions.assertEquals(e.getMessage(), "Das Buch ist zum Verkauf");
        }

        var salesList = new ArrayList<SaleDTO>();
        salesList.add(saleDTO);
        InvoiceDTO invoiceDTO = new InvoiceDTO(client1, employee1, salesList);
        var invoiceCheck = invoiceTarget.createInvoice(invoiceDTO);

        try {
            rentTarget.rentCopy(rent2);
        }
        catch (RuntimeException e){
            Assertions.assertEquals(e.getMessage(), "Das Buch ist zum Verkauf");
        }
        saleDTO = new SaleDTO(client1, employee1);
        saleDTO.getCopyList().add(copy3);
        saleDTO.getCopyList().add(copy4);
        saleTarget.createSale(saleDTO);
    }

    @Test
    @TestTransaction
    public void setOneOfTwoItemsForSale_onlyOneCanBeRented()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        pub1.getAuthors().add(author1);

        var copy1 = new CopyDTO(pub1, (SaleDTO) null, date);
        var copy2 = new CopyDTO(pub1, (SaleDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        var employee = libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck1 = libTarget.createCopy(copy1);
        var copyCheck2 = libTarget.createCopy(copy2);
        employee.setCopyAsForSale(copyCheck1);
        copy1 = new CopyDTO(copy1.getPublication(), copy1.getSale(), copy1.getDateOfPurchase(), copyCheck1.getId());
        copy2 = new CopyDTO(copy2.getPublication(), copy2.getSale(), copy2.getDateOfPurchase(), copyCheck2.getId());
        var rent1 = new RentDTO(copy1, employee1, client1);
        var rent2 = new RentDTO(copy2, employee1, client1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);

        try {
            rentTarget.rentCopy(rent1);
        }
        catch (RuntimeException e){
            Assertions.assertEquals(e.getMessage(), "Das Buch ist zum Verkauf");
        }

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(false, copyCheck1.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());

        rentTarget.rentCopy(rent2);
        Assertions.assertNotNull(publication);
        Assertions.assertEquals(true, copyCheck2.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    @Test
    @TestTransaction // WET SET 3 ITEMS FOR SALE SO NONE FOR RENT, RIGHT??
    public void setThreeDifferentItemsForSale_CustomerBuys2_InvoiceHasTwoItems_OnlyOneItemForRent()
    {
        Date date = new Date(System.currentTimeMillis());
        var mediatypeJOURNAL = new MediatypeDTO(JOURNAL, 0.99);
        var mediatypeEBOOK = new MediatypeDTO(EBOOK, 0.99);
        var mediatypeBOOK = new MediatypeDTO(BOOK, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeJOURNAL, publisherIrgendwas);
        var pub2 = new PublicationDTO("Romy ausm Wunderland",900, false, languageEN, genreFantasy, mediatypeEBOOK, publisherIrgendwas);
        var pub3 = new PublicationDTO("Romy ins Wunderland",4900, false, languageEN, genreFantasy, mediatypeBOOK, publisherIrgendwas);
        pub1.getAuthors().add(author1);
        pub2.getAuthors().add(author1);
        pub3.getAuthors().add(author1);

        var copy1 = new CopyDTO(pub1, (SaleDTO) null, date);
        var copy2 = new CopyDTO(pub2, (SaleDTO) null, date);
        var copy3 = new CopyDTO(pub3, (SaleDTO) null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeJOURNAL);
        languageTarget.createLanguage(languageEN);

        var employee = libTarget.createEmployee(employee1);
        genreTarget.createGenre(genreFantasy);
        libTarget.createPublisher(publisherIrgendwas);
        var copyCheck1 = libTarget.createCopy(copy1);
        var copyCheck2 = libTarget.createCopy(copy2);
        var copyCheck3 = libTarget.createCopy(copy3);
        employee.setCopyAsForSale(copyCheck1);
        employee.setCopyAsForSale(copyCheck2);
        employee.setCopyAsForSale(copyCheck3);
        copy1 = new CopyDTO(copy1.getPublication(), copy1.getSale(), copy1.getDateOfPurchase(), copyCheck1.getId());
        copy2 = new CopyDTO(copy2.getPublication(), copy2.getSale(), copy2.getDateOfPurchase(), copyCheck2.getId());
        copy3 = new CopyDTO(copy3.getPublication(), copy3.getSale(), copy3.getDateOfPurchase(), copyCheck3.getId());
        var rent1 = new RentDTO(copy1, employee1, client1);
        var rent2 = new RentDTO(copy2, employee1, client1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);
        var publication = pubTarget.createPublication(pub1);

        try {
            rentTarget.rentCopy(rent1);
        }
        catch (RuntimeException e){
            Assertions.assertEquals(e.getMessage(), "Das Buch ist zum Verkauf");
        }

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(false, copyCheck1.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());

        SaleDTO saleDTO = new SaleDTO(client1, employee1);
        saleDTO.getCopyList().add(copy1);
        saleDTO.getCopyList().add(copy2);
        var saleCheck = saleTarget.createSale(saleDTO);
        saleDTO = new SaleDTO(saleDTO.getClient(), saleDTO.getEmployee(), saleCheck.getId());

        var salesList = new ArrayList<SaleDTO>();
        salesList.add(saleDTO);
        InvoiceDTO invoiceDTO = new InvoiceDTO(client1, employee1, salesList);
        var invoiceCheck = invoiceTarget.createInvoice(invoiceDTO);

        try {
            rentTarget.rentCopy(rent1);
        }
        catch (RuntimeException e){
            Assertions.assertEquals(e.getMessage(), "Das Buch ist zum Verkauf");
        }

        try {
            rentTarget.rentCopy(rent2);
        }
        catch (RuntimeException e){
            Assertions.assertEquals(e.getMessage(), "Das Buch ist zum Verkauf");
        }
        Assertions.assertEquals(2, invoiceCheck.getCopiesAmount());
    }
}