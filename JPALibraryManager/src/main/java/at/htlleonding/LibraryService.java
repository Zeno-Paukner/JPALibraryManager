package at.htlleonding;
//
import at.htlleonding.model.Person;
import at.htlleonding.persistence.Author;
import at.htlleonding.persistence.Mediatype;
import at.htlleonding.persistence.Publication;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.print.attribute.standard.Media;
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

    @Transactional
    public void createAllMediatypes(){

        //-------set all publications----------
        var pub1= new Publication();
        pub1.setTitle("Romy im Wunderland");
        pub1.setMediatype(Mediatype.EBOOK);
        //----

        var pub2 = new Publication();
        pub2.setTitle("Martin in Wunderland");
        pub2.setMediatype(Mediatype.AUDIOBOOK);
        //----

        var pub3 = new Publication();
        pub3.setTitle("Zenchen im Wunderland");
        pub3.setMediatype(Mediatype.BOOK);
        //----

        var pub4 = new Publication();
        pub4.setTitle("Yimme im Wunderland");
        pub4.setMediatype(Mediatype.MAGAZINE);
        //----

        var pub5 = new Publication();
        pub5.setTitle("Willi im Wunderland");
        pub5.setMediatype(Mediatype.NEWSPAPER);
        //----

        var pub6 = new Publication();
        pub5.setTitle("Robi im Wunderland");
        pub6.setMediatype(Mediatype.REFERENCEBOOK);
        //----

        //-------set all authors-----------


        //----------commit data-----------
        entityManager.persist(pub1);
        entityManager.persist(pub2);
        entityManager.persist(pub3);
        entityManager.persist(pub4);
        entityManager.persist(pub5);
        entityManager.persist(pub6);


    }
/*
    @Transactional
    public void add(Book b, Author a) {

        var assoc = new BookAuthor(b, a);
        a.getAssocBooks().add(assoc);
        b.getAssocAuthors().add(assoc);

        entityManager.persist(assoc);
        entityManager.persist(a);
        entityManager.persist(b);
    }

 */

    public Publication getSingleMedia(Integer id) {
        try {
            return entityManager.find(Publication.class, id);
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

 /*   public Person getByAuthor(int authorId) {
        try {
            return entityManager
                    .createQuery("select p from Publication p inner join Author a on Author.Id = p.AuthorId")
                    .setParameter("name", author)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    */
}

