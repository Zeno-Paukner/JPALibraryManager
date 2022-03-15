package at.htlleonding;
//
import at.htlleonding.model.Person;
import at.htlleonding.persistence.Publication;

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
    public List<Publication> selectAll() {
        return
                entityManager
                .createQuery("select p from Publication p")
                .getResultList();
    }


    //Maybe later for data creation
    @Transactional
    public String createData() {
        return " ";
    }

    public Publication getSingleMedia(Integer id) {
        try {
            return entityManager.find(Publication.class, id);
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Person getByAuthor(int authorId) {
        try {
            return entityManager
                    .createQuery("select p from Publication p inner join Author a on a.AuthorId = p.AuthorId")
                    .setParameter("name", author)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
