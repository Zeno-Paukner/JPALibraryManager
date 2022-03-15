package at.htlleonding;
//
import at.htlleonding.model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

// @Transactional
// https://quarkus.io/guides/transaction
// https://quarkus.io/guides/hibernate-orm
// Mark your CDI bean method as @Transactional and the EntityManager will enlist and flush at commit.
// Make sure to wrap methods modifying your database (e.g. entity.persist()) within a transaction.
// Marking a CDI bean method @Transactional will do that for you and make that method a transaction boundary.
// We recommend doing so at your application entry point boundaries like your REST endpoint controllers.

@ApplicationScoped
public class LibraryService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public List<Person> selectAll() {
        return
                entityManager
                .createQuery("select p from Person p")
                .getResultList();
    }

    @Transactional
    public String createTwins() {

        List<Person> twins = new ArrayList<Person>();
        Person pers = new Person("Harry","Twain", LocalDate.of(2000, 12, 31));
        entityManager.persist(pers);
        twins.add(pers);
        pers = new Person("Marry","Twain", LocalDate.of(2000, 12, 31));
        entityManager.persist(pers);
        twins.add(pers);

        return "Congrats, Harry and Marry were born!";
    }

        public Person getSingle(Integer id) {
        try {
            return entityManager.find(Person.class, id);
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Person getByFirstName(String firstName) {
        try {
            return entityManager
                    .createQuery("select p from Person p where p.FirstName = :name", Person.class)
                    .setParameter("name", firstName)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
