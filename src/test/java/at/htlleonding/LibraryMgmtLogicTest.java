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

        var copy1 = new CopyDTO(pub1, null, date);
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

        var copy1 = new CopyDTO(pub1, null, date);
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

        var copy1 = new CopyDTO(pub1, null, date);
        var copy2 = new CopyDTO(pub2, null, date);
        var copy3 = new CopyDTO(pub3, null, date);

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
        var copy1 = new CopyDTO(pub1, null, date);
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

        var copy1 = new CopyDTO(pub1, null, date);
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

        var copy1 = new CopyDTO(pub1, null, date);
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


        var copy1 = new CopyDTO(pub1, null, date);
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


        var copy1 = new CopyDTO(pub1, null, date);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");
        var rent1 = new RentDTO(copy1, employee1, client1);

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
        rentTarget.rentCopy(rent1);

        Assertions.assertNotNull(publication);
        Assertions.assertEquals(true, copyCheck.getRented());
        Assertions.assertEquals(mediatypeJOURNAL.getMediatypeEnum(), publication.getMediatype().getMediatypeEnum());
    }

    @Test
    @TestTransaction
    public void customerRentsOneOfThreeCopiesOfRentableItem_TwoRentableItemsRemain_CustomerHasRent()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsThreeOfThreeCopiesOfRentableItem_TryRentAnother_RentNotPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerOneItemOfEachMediaType_ItemsAreRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setItemForSale_customerTriesToRent_RentNotPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setItemForOnDisplay_customerTriesToRent_RentNotPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsSingleAvailableItem_RentNotPossible_BringsBackItem_RentPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void rentOutItemToCustomerA_customerBmakesReservation_CustomerAreturnsItem_RentPossibleOnlyForCustomerB()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsItem_prolongsRentThreeTimes_customerCanOnlyProlongTwoTimes_rentalEndDate6weeksAhead()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsItem_prolongsRentTwoTimes_EmployeeProlongsOneTime_rentalEndDate8weeksAhead()
    {
        Assertions.fail("Not implemented yet");
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
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setOneOfTwoItemsForSale_onlyOneCanBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setThreeDifferentItemsForSale_CustomerBuys2_InvoiceHasTwoItems_OnlyOneItemForRent()
    {
        Assertions.fail("Not implemented yet");
    }
}