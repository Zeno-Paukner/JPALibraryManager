package at.htlleonding.Logic;

import at.htlleonding.DTOs.*;
import at.htlleonding.persistence.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class LibraryLogic {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void createSale(SaleDTO saleDTO) {
        Sale sale = new Sale();
        //check if all copys are available to purchase
        for (Integer copy_id : saleDTO.getCopy_ids()) {
            if (!checkIfCopyIsAvailableToPurchase(copy_id)) {
                throw new RuntimeException("Das Buch " + copy_id + " kann noch nicht verkauft werden");
            }
        }
        sale.setSaleDate(new Date());
        //sum all Prices form Copys with the Mediatype Price and set it to TotalPrice
        sale.setTotalPrice(saleDTO.getCopy_ids().stream().mapToDouble(copy_id -> entityManager.find(Copy.class, copy_id).getPublication().getMediatype().getPrice()).sum());

        //if Client is null the Totalprice is minus 20%
        if (saleDTO.getClient_id() == null) {
            sale.setTotalPrice(saleDTO.getTotalPrice() * 0.8);
        }
        else {
            sale.setClient(entityManager.find(Client.class, saleDTO.getClient_id()));
        }
        sale.setEmployee(entityManager.find(Employee.class, saleDTO.getEmployee_id()));
        //create sale for each copy
        for (Integer copy_id : saleDTO.getCopy_ids()) {
            sale.getCopyList().add(entityManager.find(Copy.class, copy_id));
        }
        entityManager.persist(sale);
    }

    @Transactional
    public Boolean checkifCopyofPublicationIsAvailable(Integer publication_id) {
        List<Copy> copies = (List<Copy>) entityManager.createQuery("SELECT c FROM Copy c WHERE c.publication.id = :publication_id AND c.status.id = 1", Copy.class);
        return copies.size() > 0;
    }

    @Transactional
    public Boolean checkIfCopyIsRented(Integer copy_id) {
        Copy copy = entityManager.find(Copy.class, copy_id);
        return copy.getRented() && copy.getRent().getEndDate() != null;
    }

    @Transactional
    public void rentCopy(RentDTO rentDTO) {
        if (checkifCopyofPublicationIsAvailable(entityManager.find(Copy.class, rentDTO.getCopy_id()).getPublication().getId())) {
            if (!checkIfCopyIsRented(rentDTO.getCopy_id())) {
                Rent rent = new Rent();
                List<Rent> rents = (List<Rent>) entityManager.createQuery("SELECT r FROM Rent r WHERE r.client = :client AND r.copy = :copy", Rent.class);
                if (rents.size() > 3) {
                    rent.setNeedEmployeeToRentAgain(true);
                    throw new RuntimeException("Der Kunde muss zu einen Mitarbeiter um das Buch ein weiteres Mal auszuleihen");
                }

                rent.setCopy(entityManager.find(Copy.class, rentDTO.getCopy_id()));
                rent.setStartDate(new Date());
                rent.setDeadline(new Date(new Date().getTime() + 14 * 24 * 60 * 60 * 1000));
                rent.setClient(entityManager.find(Client.class, rentDTO.getClient_id()));
                rent.setEmployee(entityManager.find(Employee.class, rentDTO.getEmployee_id()));
                entityManager.find(Copy.class, rentDTO.getCopy_id()).setRented(true);
                entityManager.persist(rent);

            } else {
                throw new RuntimeException("Das Buch ist bereits ausgeliehen");
            }
        } else {
            throw new RuntimeException("Das Buch ist nicht mehr verfügbar");
        }
    }

    @Transactional
    public void endRent(Integer rent_id) {
        Rent rent = entityManager.find(Rent.class, rent_id);
        rent.getCopy().setRented(false);
        rent.setEndDate(new Date());
        entityManager.persist(rent);
    }


    @Transactional
    public void createPublication(PublicationDTO publicationDTO) {
        //when the mediatype is a BOOK or EBOOK or AUDIOBOOK or REFERENCEBOOK then Author, Publisher Language isTranslated is required
        Publication publication = new Publication();
        if (publication.getMediatype().getId() == 1 || publication.getMediatype().getId() == 2 || publication.getMediatype().getId() == 3 || publication.getMediatype().getId() == 4) {
            if ((publication.getAuthors() == null) || (publication.getPublisher() == null) || (publication.getLanguage() == null)) {
                throw new RuntimeException("Author, Publisher, Language and isTranslated are required for this publication");
            }
        }
        //when the mediatype is a NEWSPAPER, MAGAZINE , Publisher Language isTranslated is required
        if (publication.getMediatype().getId() == 5 || publication.getMediatype().getId() == 6 || publication.getMediatype().getId() == 7) {
            if ((publication.getPublisher() == null) || (publication.getLanguage() == null)) {
                throw new RuntimeException("Publisher, Language and isTranslated are required for this publication");
            }
        }
        //fill Publication with getter from PublicationDTO
        publication.setTitle(publicationDTO.getTitle());
        publication.setPublisher(entityManager.find(Publisher.class, publicationDTO.getPublisher_id()));
        publication.setLanguage(entityManager.find(Language.class, publicationDTO.getLanguage_id()));
        publication.setTranslated(publicationDTO.isTranslated());
        publication.setMediatype(entityManager.find(Mediatype.class, publicationDTO.getMediatype_id()));
        //set genre in publication
        publication.setGenre(entityManager.find(Genre.class, publicationDTO.getGenre_id()));

        // set all PublicationDTO authors into the publication
        for (Integer author_id : publicationDTO.getAuthors_ids()) {
            Author author = entityManager.find(Author.class, author_id);
        }
        //?????????????????
        publication.setGenre(entityManager.find(Genre.class, publicationDTO.getGenre_id()));
        //publication.setAuthor(entityManager.find(Genre.class, publicationDTO.getGenre_id()));
        entityManager.persist(publication);
    }



    @Transactional
    public boolean checkIfCopyIsAvailableToPurchase(Integer copy_id) {
        //if the dateOfPurchase is 7 years ago the copy could be purchased
        return new Date().getTime() - entityManager.find(Copy.class, copy_id).getDateOfPurchase().getTime() > 7 * 365 * 24 * 60 * 60 * 1000;
    }

    @Transactional
    //createCopy
    public void createCopy(CopyDTO copyDTO) {
        Copy copy = new Copy();
        copy.setPublication(entityManager.find(Publication.class, copyDTO.getPublication_id()));
        copy.setDateOfPurchase(new Date());
        entityManager.persist(copy);
    }

    @Transactional
    public void createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setSalary(employeeDTO.getSalary());
        entityManager.persist(employee);
    }

    @Transactional
    //create Client
    public void createClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setEmail(clientDTO.getEmail());
        entityManager.persist(client);
    }

    @Transactional
    public Integer createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        entityManager.persist(author);
        return author.getId();
    }

    @Transactional
    public List<Integer> createTopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        //check every if the topic name is already in the database
        for (Topic topic1 : entityManager.createQuery("SELECT t FROM Topic t", Topic.class).getResultList()) {
            if (topic1.getKeyword().equals(topicDTO.getKeyword())) {
                throw new RuntimeException("Topic already exists");
            }
        }

        topic.setKeyword(topicDTO.getKeyword());
        entityManager.persist(topic);

        return Arrays.asList(topic.getId());
    }

    @Transactional
    public Integer createGenre(GenreDTO genreDTO) {
        Genre genre = new Genre();
        for (Genre genre1 : entityManager.createQuery("SELECT g FROM Genre g", Genre.class).getResultList()) {
            if (genre1.getGenre().equals(genreDTO.getGenre())) {
                throw new RuntimeException("Genre already exists");
            }
        }
        genre.setGenre(genreDTO.getGenre());
        entityManager.persist(genre);
        return genre.getId();
    }

    @Transactional
    public Integer createLanguage(LanguageDTO languageDTO) {
        Language language = new Language();
        //check if the language is 2 letters
        if (languageDTO.getLanguageCode().length() != 2) {
            throw new RuntimeException("Language code must be 2 letters");
        }
        language.setLanguageCode(languageDTO.getLanguageCode());
        entityManager.persist(language);
        return  language.getId();
    }

    @Transactional
    // Reservation
    public void createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setClient(entityManager.find(Client.class, reservationDTO.getClient_id()));
        reservation.setPublication(entityManager.find(Publication.class, reservationDTO.getPublication_id()));
        reservation.setReservationDate(new Date());
        entityManager.persist(reservation);
    }

    @Transactional
    //Mediatype
    public Integer createMediatype(MediatypeDTO mediatypeDTO) {
        Mediatype mediatype = new Mediatype();
        //sompare String Mediatype with enum MediatypeEnum
        for (MediatypeEnum mediatypeEnum : MediatypeEnum.values()) {
            if (mediatypeDTO.getMediatype().equals(mediatypeEnum.toString())) {
                mediatype.setMediatypeEnum(mediatypeEnum);
            }
        }
        mediatype.setPrice(mediatypeDTO.getPrice());
        entityManager.persist(mediatype);
        return mediatype.getId();
    }

    //Invoice
    @Transactional
    public void createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        //if the client is null
        invoice.setClient(entityManager.find(Client.class, invoiceDTO.getClient_id()));
        invoice.setSaleDate(new Date());
        Double totalSalePrice = 0.0;
        //create a list of all Sales
        List<Sale> sales = entityManager.createQuery("SELECT s FROM Sale s", Sale.class).getResultList();
        //sum all TotalPrice from Sales_id and save it to TotalPrice2
        for (Sale sale : sales) {
            totalSalePrice += sale.getTotalPrice();
        }
        //set the total price of the invoice
        invoice.setTotalSalesPrice(totalSalePrice);

        //sum all totalCopiesAmount from Sales_id and save it to TotalCopiesAmount
        Double totalCopiesAmount = 0.0;
        for (Sale sale : sales) {
            totalCopiesAmount += sale.getTotalCopiesAmount();
        }
        invoice.setTotalSalesPrice(totalCopiesAmount);

        entityManager.persist(invoice);
    }



}



