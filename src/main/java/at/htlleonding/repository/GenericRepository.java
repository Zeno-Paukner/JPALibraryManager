package at.htlleonding.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class GenericRepository {
    @ApplicationScoped
    public class LibraryRepository {

        @Inject
        EntityManager entityManager;

        @Transactional
        public void FlushAndClear() {
            entityManager.flush();
            entityManager.clear();
        }

        @Transactional
        public <T> void add(T entity) {
            entityManager.persist(entity);
        }

        @Transactional
        public <T> void update(T entity) {
            entityManager.merge(entity);
        }

        public <T> T find(Class<T> className, Object id) {
            return entityManager.find(className, id);
        }

        @Transactional
        public <T> void remove(T entity) {
            entityManager.remove(entity);
        }
    }
}
