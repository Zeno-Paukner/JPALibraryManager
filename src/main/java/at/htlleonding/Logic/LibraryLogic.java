package at.htlleonding.Logic;

import at.htlleonding.DTOs.CopyDTO;
import at.htlleonding.DTOs.ReservationDTO;
import at.htlleonding.DTOs.*;
import at.htlleonding.persistence.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.spi.LocaleNameProvider;

@ApplicationScoped
public class LibraryLogic {

    @Inject
    EntityManager entityManager;
    @Inject
    LanguageLogic languageLogic;
    @Inject
    GenreLogic genreLogic;
    @Inject
    RentLogic rentLogic;
    @Inject
    MediatypeLogic mediatypeLogic;


    @Transactional
    public Client createClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setEmail(clientDTO.getEmail());
        entityManager.persist(client);
        return client;
    }

    @Transactional
    public Author createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        entityManager.persist(author);
        return author;
    }

    @Transactional
    public Reservation createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setClient(entityManager.find(Client.class, reservationDTO.getClient()));
        reservation.setPublication(entityManager.find(Publication.class, reservationDTO.getPublication()));
        reservation.setReservationDate(new Date());
        entityManager.persist(reservation);

        return reservation;
    }

    @Transactional
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setSalary(employeeDTO.getSalary());
        entityManager.persist(employee);

        return employee;
    }

    @Transactional
    public Copy createCopy(CopyDTO copyDTO) {
        Copy copy = new Copy();
        PublicationDTO DTO = copyDTO.getPublication();
        copy.setPublication(entityManager.find(Publication.class, DTO.getId()));
        copy.setDateOfPurchase(new Date());
        entityManager.persist(copy);
        return copy;
    }

    @Transactional
    public Publisher createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = new Publisher(publisherDTO.getId(), publisherDTO.getPublisherName());
        entityManager.persist(publisher);
        return publisher;
    }
}



