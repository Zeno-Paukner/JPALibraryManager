package at.htlleonding.Logic;

import at.htlleonding.DTOs.RentDTO;
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
    public void rentCopy(RentDTO rentDTO) {
        //if (checkifCopyofPublicationIsAvailable(entityManager.find(Copy.class, rentDTO.getCopy()).getPublication().getId())) {

        var copy = entityManager.find(Copy.class, rentDTO.getCopy().getId());
        var client = entityManager.find(Client.class, rentDTO.getClient().getId());

        if (!copy.getRented()) {

            Rent rent = new Rent();
            List<Rent> rents = entityManager.createQuery("SELECT r FROM Rent r WHERE r.client = ?1 AND r.copy = ?2", Rent.class)
                    .setParameter(1, client.getId())
                    .setParameter(2, copy.getId())
                    .getResultList();
            if (rents.size() > 3) {
                rent.setNeedEmployeeToRentAgain(true);
                throw new RuntimeException("Der Kunde muss zu einen Mitarbeiter um das Buch ein weiteres Mal auszuleihen");
            }

            rent.setCopy(copy);
            rent.setStartDate(new Date());
            rent.setDeadline(new Date(new Date().getTime() + 14 * 24 * 60 * 60 * 1000));
            rent.setClient(entityManager.find(Client.class, rentDTO.getClient()));
            rent.setEmployee(entityManager.find(Employee.class, rentDTO.getEmployee()));
            copy.setRented(true);
            entityManager.persist(copy);
            entityManager.persist(rent);

        } else {
            throw new RuntimeException("Das Buch ist bereits ausgeliehen");
        }
    }

    //end Rent by Copy object
    @Transactional
    public void returnCopy(Copy copy) {
        Rent rent = entityManager.find(Rent.class, copy.getRent());
        rent.getCopy().setRented(false);
    }
}
