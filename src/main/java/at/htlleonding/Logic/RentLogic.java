package at.htlleonding.Logic;

import at.htlleonding.DTOs.*;
import at.htlleonding.persistence.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class RentLogic {
    @Inject
    EntityManager entityManager;
    @Inject
    LibraryLogic libraryLogic;
    @Inject
    ClientLogic clientLogic;


    //check if a publication has a copy available

    //@Transactional
    //public Boolean checkifCopyofPublicationIsAvailable(Integer publication_id) {
    //    List<Copy> copies = (List<Copy>) entityManager.createQuery("SELECT c FROM Copy c WHERE c.publication.id = :publication_id AND c.status.id = 1", Copy.class);
    //    return copies.size() > 0;
    //}


    /*@Transactional
    public Boolean checkIfCopyIsRented(Integer copy_id) {
        Copy copy = entityManager.find(Copy.class, copy_id);
        return copy.getRented() && copy.getRent().getEndDate() != null;
    }*/

    @Transactional
    public Rent rentCopy(RentDTO rentDTO) {
        //if (checkifCopyofPublicationIsAvailable(entityManager.find(Copy.class, rentDTO.getCopy()).getPublication().getId())) {

        var copy = entityManager.createQuery("SELECT c FROM Copy c WHERE c.id = ?1", Copy.class)
                .setParameter(1, rentDTO.getCopy().getId())
                .getSingleResult();
        var client = entityManager.createQuery("SELECT c FROM Client c WHERE c.firstName = ?1 and c.lastName = ?2 and c.phoneNumber = ?3 and c.email = ?4", Client.class)
                .setParameter(1, rentDTO.getClient().getFirstName())
                .setParameter(2, rentDTO.getClient().getLastName())
                .setParameter(3, rentDTO.getClient().getPhoneNumber())
                .setParameter(4, rentDTO.getClient().getEmail())
                .getSingleResult();

        if ((copy.getClientReservedBy() == null || copy.getClientReservedBy() == client) &&!copy.getOnDisplay() && !copy.getForSale() && !copy.getRented()) {

            Rent rent = new Rent();
            List<Rent> rents = entityManager.createQuery("SELECT r FROM Rent r WHERE r.copy.id = ?1 and r.client.phoneNumber = ?2", Rent.class)
                    .setParameter(1, copy.getId())
                    .setParameter(2, client.getPhoneNumber())
                    .getResultList();
            if (rents.size() > 3) {
                throw new RuntimeException("Der Kunde muss zu einen Mitarbeiter um das Buch ein weiteres Mal auszuleihen");
            }

            rent.setCopy(copy);
            rent.setStartDate(new Date());
            rent.setDeadline(new Date(new Date().getTime() + 14 * 24 * 60 * 60 * 1000));
            rent.setClient(client);
            rent.setEmployee(entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName = ?1 and e.firstName = ?2", Employee.class)
                    .setParameter(1, rentDTO.getEmployee().getLastName())
                    .setParameter(2, rentDTO.getEmployee().getFirstName())
                    .getSingleResult());
            copy.setRented(true);
            copy.setClientReservedBy(null);
            entityManager.persist(copy);
            entityManager.persist(rent);
            return rent;
        } else {
            if (copy.getRented())
                throw new RuntimeException("Das Buch ist bereits ausgeliehen");
            else if (copy.getForSale())
                throw new RuntimeException("Das Buch ist zum Verkauf");
            else if (copy.getOnDisplay())
                throw new RuntimeException("Das Buch ist ausgestellt");
            else if (copy.getClientReservedBy() != client)
                throw new RuntimeException("Das Buch ist reserviert");
            else
                throw new RuntimeException();
        }
    }

    //end Rent by Copy object
    @Transactional
    public void returnCopy(RentDTO rentDTO) {
        Rent rent = entityManager.createQuery("SELECT r FROM Rent r WHERE r.id = ?1", Rent.class)
                .setParameter(1, rentDTO.getId())
                .getSingleResult();
        rent.getCopy().setRented(false);
    }

    @Transactional
    public void reserveCopy(RentDTO rentDTO){
        var copy = entityManager.createQuery("SELECT c FROM Copy c WHERE c.id = ?1", Copy.class)
                .setParameter(1, rentDTO.getCopy().getId())
                .getSingleResult();

        var client = entityManager.createQuery("SELECT c FROM Client c WHERE c.firstName = ?1 and c.lastName = ?2 and c.phoneNumber = ?3 and c.email = ?4", Client.class)
                .setParameter(1, rentDTO.getClient().getFirstName())
                .setParameter(2, rentDTO.getClient().getLastName())
                .setParameter(3, rentDTO.getClient().getPhoneNumber())
                .setParameter(4, rentDTO.getClient().getEmail())
                .getSingleResult();

        copy.setClientReservedBy(client);
    }

    @Transactional
    public void prolongRent(RentDTO rentDTO){
        Rent rent = entityManager.createQuery("SELECT r FROM Rent r WHERE r.id = ?1", Rent.class)
                .setParameter(1, rentDTO.getId())
                .getSingleResult();

        if (rent.getProlongedCounter() < 2){
            rent.getDeadline().setTime(rent.getDeadline().getTime() + 1000 * 60 * 60 * 24 * 7 * 2);
            rent.setProlongedCounter(rent.getProlongedCounter()+1);
        }
        else{
            throw new RuntimeException("Muss von Mitarbeiter verlängert werden");
        }
    }

    @Transactional
    public void prolongRent(RentDTO rentDTO, Employee employee){
        Rent rent = entityManager.createQuery("SELECT r FROM Rent r WHERE r.id = ?1", Rent.class)
                .setParameter(1, rentDTO.getId())
                .getSingleResult();

        if (employee != null){
            rent.getDeadline().setTime(rent.getDeadline().getTime() + 1000 * 60 * 60 * 24 * 7 * 2);
            rent.setProlongedCounter(rent.getProlongedCounter()+1);
        }
        else{
            throw new RuntimeException("Muss von Mitarbeiter verlängert werden");
        }
    }
}
