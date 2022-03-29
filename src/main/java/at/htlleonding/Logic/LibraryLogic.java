package at.htlleonding.Logic;

import at.htlleonding.DTOs.RentDTO;
import at.htlleonding.DTOs.SaleDTO;
import at.htlleonding.persistence.*;
import at.htlleonding.repository.GenericRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class LibraryLogic {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void createSalewithMulipleCopys(SaleDTO saleDTO) {
        Sale sale = new Sale();
        sale.setSaleDate(new Date());
        sale.setClient(entityManager.find(Client.class, saleDTO.getClient_id()));
        sale.setEmployee(entityManager.find(Employee.class, saleDTO.getEmployee_id()));
        sale.getCopyList().addAll(saleDTO.getCopy_id());

        entityManager.persist(sale);
    }

    public Boolean checkifCopyofPublicationIsAvailable(Integer publication_id) {
        List<Copy> copies = (List<Copy>) entityManager.createQuery("SELECT c FROM Copy c WHERE c.publication.id = :publication_id AND c.status.id = 1", Copy.class);
        return copies.size() > 0;
    }

    public Boolean checkiftheCopyIsRented(Integer copy_id) {
        List<Rent> rents = (List<Rent>) entityManager.createQuery("SELECT r FROM Rent r WHERE r.copy.id = :copy_id AND r.endDate IS NULL", Rent.class);
        return rents.size() > 0;
    }


    public void rentCopy(RentDTO rentDTO) {
        if (checkifCopyofPublicationIsAvailable(entityManager.find(Copy.class, rentDTO.getCopy_id()).getPublication().getId())) {
            if (!checkiftheCopyIsRented(rentDTO.getCopy_id())) {
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
                entityManager.persist(rent);

            } else {
                    throw new RuntimeException("Das Buch ist bereits ausgeliehen");
                }
        } else {
                throw new RuntimeException("Das Buch ist nicht mehr verfügbar");
            }
    }

        public void endRent (Integer id){
            Rent rent = entityManager.find(Rent.class, id);
            rent.setEndDate(new Date());
            entityManager.persist(rent);
        }

        public void createPublication (Publication publication){
            //when the mediatype is a BOOK or EBOOK or AUDIOBOOK or REFERENCEBOOK then Author, Publisher Language isTranslated is required
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


            entityManager.persist(publication);
        }

        public boolean checkIfCopyisAvailableToPurchase (Integer copy_id){
            //if the dateOfPurchase is 7 years ago the copy could be purchased
            return new Date().getTime() - entityManager.find(Copy.class, copy_id).getDateOfPurchase().getTime() > 7 * 365 * 24 * 60 * 60 * 1000;
        }


    }


}
