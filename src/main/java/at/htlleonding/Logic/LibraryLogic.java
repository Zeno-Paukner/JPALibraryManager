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

    public void rentCopy(RentDTO rentDTO) {
        Rent rent = new Rent();
        rent.setCopy(entityManager.find(Copy.class, rentDTO.getCopy_id()));
        rent.setStartDate(new Date());
        rent.setDeadline(new Date(new Date().getTime() + 14 * 24 * 60 * 60 * 1000));
        rent.setClient(entityManager.find(Client.class, rentDTO.getClient_id()));
        rent.setEmployee(entityManager.find(Employee.class, rentDTO.getEmployee_id()));
        List<Rent> rents = (List<Rent>) entityManager.createQuery("SELECT r FROM Rent r WHERE r.client = :client AND r.copy = :copy", Rent.class);
        if (rents.size() > 3) {
            rent.setNeedEmployeeToRentAgain(true);
            throw new RuntimeException("Der Kunde muss zu einen Mitarbeiter um das Buch ein weiteres Mal auszuleihen");
        }




        entityManager.persist(rent);
    }

    public void endRent(Integer id) {
        Rent rent = entityManager.find(Rent.class, id);
        rent.setEndDate(new Date());
        entityManager.persist(rent);
    }



}
















}
