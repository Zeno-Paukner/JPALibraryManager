package at.htlleonding;
//
import at.htlleonding.model.Person;
import at.htlleonding.persistence.*;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.print.attribute.standard.Media;
import javax.transaction.TransactionScoped;
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

        /*//-------set all publications----------
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
        //----*/

        //-------set all authors-----------


        //----------commit data-----------
        /*entityManager.persist(pub1);
        entityManager.persist(pub2);
        entityManager.persist(pub3);
        entityManager.persist(pub4);
        entityManager.persist(pub5);
        entityManager.persist(pub6);*/


    }

    @Transactional
    public void add(Author a) { entityManager.persist(a);  }

    @Transactional
    public void add(Publication p) { entityManager.persist(p);  }

    @Transactional
    public void add(Genre g) { entityManager.persist(g);  }

    @Transactional
    public void add(Publisher pu) { entityManager.persist(pu);  }

    @Transactional
    public void add(Language lang){ entityManager.persist(lang); }

    @Transactional
    public void add(Reservation r){ entityManager.persist(r); }

    @Transactional
    public void add(Topic t) { entityManager.persist(t); }

    @Transactional
    public void add(Publication p, Author a) {
        if(p.getId() == null)
            add(p);

        if(a.getId() == null)
            add(a);
        p.getAuthors().add(a);
        a.getPublications().add(p);

        entityManager.persist(a);
        entityManager.persist(p);
    }

    @Transactional
    public void add(Publication p, Topic t){
        if(p.getId() == null){
            add(p);
        }

        if(t.getId() == null){
            add(t);
        }

        t.getPublications().add(p);
        p.getTopics().add(t);

        entityManager.persist(t);
        entityManager.persist(p);

    }

    @Transactional
    public void add(Publication p, Publisher pu){
        if(p.getId() == null){
            add(p);
        }
        if(pu.getId() == null){
            add(pu);
        }
        p.getPublisher().add(pu);
        pu.getPublications().add(p);

        entityManager.persist(pu);
        entityManager.persist(p);
    }

    @Transactional
    public void add(Publication p, Language lang){
        if(p.getId() == null){
            add(p);
        }
        if(lang.getId() == null){
            add(lang);
        }
        p.getLanguages().add(lang);
        lang.getPublications().add(p);

        entityManager.persist(p);
        entityManager.persist(lang);
    }

    @Transactional
    public void add(Publication p, Reservation r){
        if(p.getId() == null){
            add(p);
        }
        if(r.getId() == null){
            add(r);
        }
        p.getReservations().add(r);
        r.getPublications().add(p);

        entityManager.persist(p);
        entityManager.persist(r);

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

 public Publication getByAuthor(int authorId) {
        try {
            return entityManager
                    .createQuery("select p from Publication p join p.authors a on a.author.id = ?1", Publication.class)
                    .setParameter(1, authorId)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}

