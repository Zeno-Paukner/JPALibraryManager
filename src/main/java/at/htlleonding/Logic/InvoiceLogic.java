package at.htlleonding.Logic;

import at.htlleonding.DTOs.InvoiceDTO;
import at.htlleonding.persistence.Client;
import at.htlleonding.persistence.Invoice;
import at.htlleonding.persistence.Sale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class InvoiceLogic {
    @Inject
    EntityManager entityManager;

    @Transactional
    public void createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();

        invoice.setClient(entityManager.find(Client.class, invoiceDTO.getClient()));
        invoice.setSaleDate(new Date());
        Double totalSalePrice = 0.0;
        //create a list of all Sales
        List<Sale> sales = entityManager.createQuery("SELECT s FROM Sale s WHERE s.id IN (:sale_ids)", Sale.class)
                .setParameter("sale_ids", invoiceDTO.getSale()).getResultList();

        //sum all TotalPrice from Sales_id and save it to TotalPrice2
        for (Sale sale : sales) {
            totalSalePrice += sale.getTotalPrice();
        }
        //set the total price of the invoice
        invoice.setTotalSalesPrice(totalSalePrice);

        //sum all totalCopiesAmount from Sales_id and save it to TotalCopiesAmount
        Double totalCopiesAmount = 0.0;
        for (Sale sale : sales) {
            totalCopiesAmount += sale.getTotalCopiesAmount();
        }
        invoice.setTotalSalesPrice(totalCopiesAmount);

        entityManager.persist(invoice);
    }
}
