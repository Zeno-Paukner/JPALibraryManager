package at.htlleonding.Logic;

import at.htlleonding.DTOs.InvoiceDTO;
import at.htlleonding.persistence.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class InvoiceLogic {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Invoice createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();

        invoice.setClient(entityManager.createQuery("SELECT c FROM Client c WHERE c.phoneNumber = ?1 and c.email = ?2 and c.firstName = ?3 and c.lastName = ?4", Client.class)
                .setParameter(1, invoiceDTO.getClient().getPhoneNumber())
                .setParameter(2, invoiceDTO.getClient().getEmail())
                .setParameter(3, invoiceDTO.getClient().getFirstName())
                .setParameter(4, invoiceDTO.getClient().getLastName())
                .getSingleResult());
        invoice.setEmployee(entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName = ?1 and e.firstName = ?2 and e.salary = ?3", Employee.class)
                .setParameter(1, invoiceDTO.getEmployee().getLastName())
                .setParameter(2, invoiceDTO.getEmployee().getFirstName())
                .setParameter(3, invoiceDTO.getEmployee().getSalary())
                .getSingleResult());
        invoice.setSaleDate(new Date());
        Double totalSalePrice = 0.0;
        //create a list of all Sales
        List<Sale> sales = new ArrayList<>();
        for (var s : invoiceDTO.getSales()){
            sales.add(entityManager.createQuery("SELECT s FROM Sale s WHERE s.id = ?1", Sale.class)
                    .setParameter(1, s.getId())
                    .getSingleResult());
        }

        //sum all TotalPrice from Sales_id and save it to TotalPrice2
        for (Sale sale : sales) {
            totalSalePrice += sale.getTotalPrice();
        }
        //set the total price of the invoice
        invoice.setTotalSalesPrice(totalSalePrice);

        //sum all totalCopiesAmount from Sales_id and save it to TotalCopiesAmount
        int totalCopiesAmount = 0;
        for (Sale sale : sales) {
            totalCopiesAmount += sale.getTotalCopiesAmount();
        }
        invoice.setCopiesAmount(totalCopiesAmount);
        entityManager.persist(invoice);
        return invoice;
    }
}
