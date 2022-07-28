package at.htlleonding.repository;
//
import at.htlleonding.XMLDTOs.WorksOfAuthorDTO;
import at.htlleonding.persistence.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class LibraryRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void FlushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

    //create a generic add method for all entities


    public Sale findSale(int id) {
        return entityManager.find(Sale.class, id);
    }

    @Transactional
    public void flush() {
        entityManager.flush();
    }

    @Transactional
    public void clear() {
        entityManager.clear();
    }

    @Transactional
    public <T> void add(T entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public <E> Boolean contains(E e){
        return entityManager.contains(e);
    }

    @Transactional
    public void add(Author a) {
        entityManager.persist(a);
    }

    @Transactional
    public void add(Publication p) {
        entityManager.persist(p);
    }
    @Transactional
    public void add(Mediatype m){
        entityManager.persist(m);
    }

    @Transactional
    public void add(Genre g) {
        entityManager.persist(g);
    }

    @Transactional
    public void add(Publisher pu) {
        entityManager.persist(pu);
    }

    @Transactional
    public void add(Language lang) {
        entityManager.persist(lang);
    }

    @Transactional
    public void add(Reservation r) {
        entityManager.persist(r);
    }

    @Transactional
    public void add(Topic t) {
        entityManager.persist(t);
    }

    @Transactional
    public void add(Employee e){
        entityManager.persist(e);
    }

    @Transactional
    public void add(Client c){
        entityManager.persist(c);
    }

    @Transactional
    public void add(Publication p, Author a) {
        if (p.getId() == null)
            add(p);

        if (a.getId() == null)
            add(a);
        p.getAuthors().add(a);
        a.getPublications().add(p);

        entityManager.persist(a);
        entityManager.persist(p);
    }

    @Transactional
    public void add(Publication p, Mediatype m) {
        if (p.getId() == null)
            add(p);

        if (m.getId() == null)
            add(m);

        p.setMediatype(m);
        m.getPublications().add(p);

        entityManager.persist(m);
        entityManager.persist(p);
    }

//    @Transactional
//    public void add(Publication p, Topic t) {
//        if (p.getId() == null) {
//            add(p);
//        }
//
//        if (t.getId() == null) {
//            add(t);
//        }
//
//        t.getPublications().add(p);
//        p.getTopics().add(t);
//
//        entityManager.persist(t);
//        entityManager.persist(p);
//
//    }

    @Transactional
    public void add(Publication p, Publisher pu) {
        if (p.getId() == null) {
            add(p);
        }
        if (pu.getId() == null) {
            add(pu);
        }
        p.setPublisher(pu);
        pu.getPublications().add(p);

        entityManager.persist(pu);
        entityManager.persist(p);
    }

    @Transactional
    public void add(Publication p, Language lang) {
        if (p.getId() == null) {
            add(p);
        }
        if (lang.getId() == null) {
            add(lang);
        }
        p.setLanguage(lang);
        lang.getPublications().add(p);

        entityManager.persist(p);
        entityManager.persist(lang);
    }

    @Transactional
    public void add(Publication p, Genre g) {
        if (p.getId() == null) {
            add(p);
        }
        if (g.getId() == null) {
            add(g);
        }
        p.setGenre(g);
        g.getPublications().add(p);

        entityManager.persist(p);
        entityManager.persist(g);
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
        r.setPublication(p);

        entityManager.persist(p);
        entityManager.persist(r);

    }

    @Transactional
    public <E> void remove(E e) {entityManager.remove(e);}

    public Publication getSingleMedia(Integer id) {
        try {
            return entityManager.find(Publication.class, id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Author> getAllAuthors() {
        try {
            return entityManager
                    .createQuery("select a from Author a", Author.class)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Author getAuthorByLastName(String lastname) {
        try {
            return entityManager
                    .createQuery("select a from Author a where a.lastName = ?1", Author.class)
                    .setParameter(1, lastname)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Publication> getAllPublications() {
        try {
            return entityManager
                    .createQuery("select p from Publication p", Publication.class)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Publication getPublicationByTitle(String title) {
        try {
            return entityManager
                    .createQuery("select p from Publication p where p.title = ?1", Publication.class)
                    .setParameter(1, title)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Topic> getAllTopics() {
        try {
            return entityManager
                    .createQuery("select t from Topic t", Topic.class)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Publication> getPublicationsByTopic(String topic) {
        try {
            return entityManager
                    .createQuery("select p from Publication p join p.topics t on t.keyword = ?1", Publication.class)
                    .setParameter(1, topic)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Genre> getAllGenres() {
        try {
            return entityManager
                    .createQuery("select g from Genre g", Genre.class)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Publication> getPublicationsByGenre(String genre) {
        try {
            return entityManager
                    .createQuery("select p from Publication p join p.genre g on g.genre = ?1", Publication.class)
                    .setParameter(1, genre)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;

        }
    }

    @Transactional
    public List<Publisher> getAllPublisher() {
        try {
            return entityManager
                    .createQuery("select p from Publisher p", Publisher.class)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Publisher getPublisherByName(String publisherName) {
        try {
            return entityManager
                    .createQuery("select p from Publisher p where p.publisherName = ?1", Publisher.class)
                    .setParameter(1, publisherName)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Language> getAllLanguages() {
        try {
            return entityManager
                    .createQuery("select l from Language l", Language.class)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Language getLanguageByLanguageCode(String languageCode) {
        try {
            return entityManager
                    .createQuery("select l from Language l where l.languageCode = ?1", Language.class)
                    .setParameter(1, languageCode)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Reservation> getAllReservations() {
        try {
            return entityManager
                    .createQuery("select r from Reservation r", Reservation.class)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Client> getAllClients() {
        try {
            return entityManager
                    .createQuery("select c from Client c", Client.class)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public List<Employee> getAllEmployees() {
        try {
            return entityManager
                    .createQuery("select e from Employee e", Employee.class)
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Transactional
    public Publication getPublicationById(Integer id) {
        try {
            return entityManager
                    .createQuery("select p from Publication p where p.id = ?1", Publication.class)
                    .setParameter(1, id)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public String convertToXml(WorksOfAuthorDTO worksOfAuthorDTO) {
        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            return xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(worksOfAuthorDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
    public void exportToXml(String filepath, String data) throws IOException {
        Path path = Paths.get(filepath);

        Files.writeString(path, data, StandardCharsets.UTF_8);
    }
}