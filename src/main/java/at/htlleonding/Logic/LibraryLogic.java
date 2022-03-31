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
    // Reservation
    public void createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setClient(entityManager.find(Client.class, reservationDTO.getClient_id()));
        reservation.setPublication(entityManager.find(Publication.class, reservationDTO.getPublication_id()));
        reservation.setReservationDate(new Date());
        entityManager.persist(reservation);
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
    //createCopy
    public Integer createCopy(CopyDTO copyDTO) {
        Copy copy = new Copy();
        copy.setPublication(entityManager.find(Publication.class, copyDTO.getPublication_id()));
        copy.setDateOfPurchase(new Date());
        entityManager.persist(copy);
        return copy.getId();
    }

    //createPublisher
    @Transactional
    public Integer createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = new Publisher();
        publisher.setPublisherName(publisherDTO.getPublisherName());
        entityManager.persist(publisher);
        return publisher.getId();
    }

}



