package at.htlleonding.Logic;

import at.htlleonding.DTOs.RentDTO;
import at.htlleonding.persistence.Client;
import at.htlleonding.persistence.Copy;
import at.htlleonding.persistence.Employee;
import at.htlleonding.persistence.Rent;

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
    LibraryLogic libraryLogic;


    //check if a publication has a copy available

    //@Transactional
    //public Boolean checkifCopyofPublicationIsAvailable(Integer publication_id) {
    //    List<Copy> copies = (List<Copy>) entityManager.createQuery("SELECT c FROM Copy c WHERE c.publication.id = :publication_id AND c.status.id = 1", Copy.class);
    //    return copies.size() > 0;
    //}

    //check if a copy is available with copy object with  isRented
    public Boolean checkifCopyIsAvailable(Copy copy) {
        return copy.getRented();
    }



    /*@Transactional
    public Boolean checkIfCopyIsRented(Integer copy_id) {
        Copy copy = entityManager.find(Copy.class, copy_id);
        return copy.getRented() && copy.getRent().getEndDate() != null;
    }*/

    @Transactional
    public void rentCopy(RentDTO rentDTO) {
        //if (checkifCopyofPublicationIsAvailable(entityManager.find(Copy.class, rentDTO.getCopy()).getPublication().getId())) {
        if (!checkifCopyIsAvailable(libraryLogic.createCopy(rentDTO.getCopy()))) {

            //if (checkifCopyofPublicationIsAvailable(entityManager.find(Copy.class, rentDTO.getCopy_id()).getPublication().getId())) {
            //if (!checkIfCopyIsRented(rentDTO.getCopy_id())) {

                Rent rent = new Rent();
                List<Rent> rents = (List<Rent>) entityManager.createQuery("SELECT r FROM Rent r WHERE r.client = :client AND r.copy = :copy", Rent.class);
                if (rents.size() > 3) {
                    rent.setNeedEmployeeToRentAgain(true);
                    throw new RuntimeException("Der Kunde muss zu einen Mitarbeiter um das Buch ein weiteres Mal auszuleihen");
                }

                rent.setCopy(entityManager.find(Copy.class, rentDTO.getCopy()));
                rent.setStartDate(new Date());
                rent.setDeadline(new Date(new Date().getTime() + 14 * 24 * 60 * 60 * 1000));
                rent.setClient(entityManager.find(Client.class, rentDTO.getClient()));
                rent.setEmployee(entityManager.find(Employee.class, rentDTO.getEmployee()));
                entityManager.find(Copy.class, rentDTO.getCopy()).setRented(true);
                entityManager.persist(rent);

            } else {
                throw new RuntimeException("Das Buch ist bereits ausgeliehen");
            }
            //} else {
            //    throw new RuntimeException("Das Buch ist nicht mehr verfügbar");
            //}
        }




    //end Rent by Copy object
    @Transactional
    public void endRentCopy(Copy copy) {
        Rent rent = entityManager.find(Rent.class, copy.getRent().getId());
        rent.getCopy().setRented(false);
    }

}
