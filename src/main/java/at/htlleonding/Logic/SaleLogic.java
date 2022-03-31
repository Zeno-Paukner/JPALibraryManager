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
        return new Date().getTime() - entityManager.find(Copy.class, copy_id).getDateOfPurchase().getTime() > 7 * 365 * 24 * 60 * 60 * 1000;
    }

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
}