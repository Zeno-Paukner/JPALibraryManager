import at.htlleonding.DTOs.SaleCopyDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class LibraryLogic {

    @Inject
    EntityManager entityManager;

/*    addCopystoSale(SaleCopyDTO saleDTO){
        Sale sale = entityManager.find(Sale.class, saleDTO.getId());
        for(SaleCopyDTO copyDTO : saleDTO.getSaleCopies()){
            SaleCopy copy = entityManager.find(SaleCopy.class, copyDTO.getId());
            sale.addCopy(copy);
        }
        entityManager.persist(sale);
    }

    }


    }*/













}
