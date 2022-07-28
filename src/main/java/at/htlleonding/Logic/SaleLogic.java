package at.htlleonding.Logic;

import at.htlleonding.DTOs.SaleDTO;
import at.htlleonding.persistence.Client;
import at.htlleonding.persistence.Copy;
import at.htlleonding.persistence.Employee;
import at.htlleonding.persistence.Sale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;

@ApplicationScoped
public class SaleLogic {
    @Inject
    EntityManager entityManager;

    @Transactional
    public boolean checkIfCopyIsAvailableToPurchase(Integer copy_id) {
        //if the dateOfPurchase is 7 years ago the copy could be purchased
        return entityManager.find(Copy.class, copy_id).getForSale();
    }

    @Transactional
    public Sale createSale(SaleDTO saleDTO) {
        Sale sale = new Sale();

        // check if all copies are available to purchase
        for (var copy : saleDTO.getCopyList()) {
            var copy_id = copy.getId();
            if (!checkIfCopyIsAvailableToPurchase(copy_id)) {
                throw new RuntimeException("Das Buch " + copy_id + " kann noch nicht verkauft werden");
            }
        }
        sale.setSaleDate(new Date());
        // sum all Prices from copies with the mediatype Price and set it to TotalPrice
        double totalPrice = saleDTO.getCopyList().stream().mapToDouble(copy -> copy.getPublication().getMediatype().getPrice()).sum();
        sale.setTotalPrice(totalPrice);

        // if Client is null the total price is minus 20%
        if (saleDTO.getClient() == null) {
            sale.setTotalPrice(totalPrice * 0.8);
        }
        else {
            sale.setClient(entityManager.createQuery("SELECT c FROM Client c WHERE c.phoneNumber = ?1 and c.email = ?2 and c.firstName = ?3 and c.lastName = ?4", Client.class)
                    .setParameter(1, saleDTO.getClient().getPhoneNumber())
                    .setParameter(2, saleDTO.getClient().getEmail())
                    .setParameter(3, saleDTO.getClient().getFirstName())
                    .setParameter(4, saleDTO.getClient().getLastName())
                    .getSingleResult());
        }
        sale.setEmployee(entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName = ?1 and e.firstName = ?2 and e.salary = ?3", Employee.class)
                .setParameter(1, saleDTO.getEmployee().getLastName())
                .setParameter(2, saleDTO.getEmployee().getFirstName())
                .setParameter(3, saleDTO.getEmployee().getSalary())
                .getSingleResult());
        // create sale for each copy
        for (var copy : saleDTO.getCopyList()) {
            var copy_id = copy.getId();
            sale.getCopyList().add(entityManager.find(Copy.class, copy_id));
        }
        entityManager.persist(sale);
        return sale;
    }
}
