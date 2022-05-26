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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static at.htlleonding.persistence.MediatypeEnum.BOOK;

@QuarkusTest
public class LibraryMgmtLogicTest {

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
        var mediatypeBOOK = new MediatypeDTO(BOOK, 0.99);
        var languageEN = new LanguageDTO("EN");
        var genreFantasy = new GenreDTO("Fantasy");
        var publisherIrgendwas = new PublisherDTO("IrgendwasHilfe");
        var author1 = new AuthorDTO("Romeo", "Bhuiyan");
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        authorDTOList.add(author1);
        var pub1 = new PublicationDTO("Romy im Wunderland",1900, true, languageEN, genreFantasy, mediatypeBOOK, publisherIrgendwas, authorDTOList);

        var copy1 = new CopyDTO(pub1, null, 0);
        var employee1 = new EmployeeDTO("Zeno", "Paukner", 1000);
        var client1 = new ClientDTO("Martin", "Hausleitner", "+43 03123923912", "martin@waffal.at");
        var rent1 = new RentDTO(copy1, employee1, client1);

        target.FlushAndClear();

        mediaTarget.createMediatype(mediatypeBOOK);
        languageTarget.createLanguage(languageEN);
        genreTarget.createGenre(genreFantasy);
        // libTarget.createCopy(copy1);
        libTarget.createPublisher(publisherIrgendwas);
        pubTarget.createPublication(pub1);
        libTarget.createAuthor(author1);
        libTarget.createClient(client1);

        rentTarget.rentCopy(rent1);


    }

    @Test
    @TestTransaction
    public void addPaperBookWithThreeAuthors_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addThreeCopiesOfPaperBookWithThreeAuthors_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addNewspaperWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addAudioBookWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addEBookWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addJournalWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
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
    }

    @Test
    @TestTransaction
    public void addLibraryEmployee_isAvailable()
    {
        Assertions.fail("Not implemented yet");
    }

    /*
     Rent out, bring back, reserve and prolong.
     Verify state of rented items and customer's rent list.
    */
    @Test
    @TestTransaction
    public void customerRentsRentableItem_ItemIsRented()
    {
        Assertions.fail("Not implemented yet");
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