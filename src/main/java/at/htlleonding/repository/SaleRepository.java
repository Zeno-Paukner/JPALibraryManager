package at.htlleonding.repository;

import at.htlleonding.persistence.Sale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class SaleRepository {

    @Inject
    EntityManager entityManager;

    public Sale findSale(int id) {
        return entityManager.find(Sale.class, id);
    }
}
