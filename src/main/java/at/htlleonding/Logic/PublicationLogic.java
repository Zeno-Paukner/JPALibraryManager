package at.htlleonding.Logic;
import at.htlleonding.DTOs.*;
import at.htlleonding.persistence.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;

@ApplicationScoped
public class PublicationLogic {

    @Inject
    EntityManager entityManager;

    @Transactional
    public Publication createPublication(PublicationDTO publicationDTO) {
        //when the mediatype is a BOOK or EBOOK or AUDIOBOOK or REFERENCEBOOK then Author, Publisher Language isTranslated is required
        Publication publication = new Publication();
        if (publication.getMediatype().getId() == 1 || publication.getMediatype().getId() == 2 || publication.getMediatype().getId() == 3 || publication.getMediatype().getId() == 4) {
            if ((publication.getAuthors() == null) || (publication.getPublisher() == null) || (publication.getLanguage() == null)) {
                throw new RuntimeException("Author, Publisher, Language and isTranslated are required for this publication");
            }
        }
        //when the mediatype is a NEWSPAPER, MAGAZINE , Publisher Language isTranslated is required
        if (publication.getMediatype().getId() == 5 || publication.getMediatype().getId() == 6 || publication.getMediatype().getId() == 7) {
            if ((publication.getPublisher() == null) || (publication.getLanguage() == null)) {
                throw new RuntimeException("Publisher, Language and isTranslated are required for this publication");
            }
        }
        //fill Publication with getter from PublicationDTO
        publication.setTitle(publicationDTO.getTitle());

        publication.setPublisher(entityManager.createQuery("select pub from Publisher pub where pub.id = ?1", Publisher.class)
                .setParameter(1, publicationDTO.getPublisher().getId()).getSingleResult());

        publication.setLanguage(entityManager.createQuery("select lang from Language lang where lang.id = ?1", Language.class)
                .setParameter(1, publicationDTO.getLanguage().getId()).getSingleResult());

        publication.setMediatype(entityManager.createQuery("select media from Language media where media.id = ?1", Mediatype.class)
                .setParameter(1, publicationDTO.getMediatype().getId()).getSingleResult());
        //set genre in publication
        publication.setGenre(entityManager.createQuery("select g from Genre g where g.id = ?1", Genre.class)
                .setParameter(1, publicationDTO.getGenre().getId()).getSingleResult());

        publication.setTranslated(publicationDTO.getIsTranslated());
        publication.setMediatype(entityManager.createQuery("select media from Mediatype media where media.id = ?1", Mediatype.class)
                .setParameter(1, publicationDTO.getMediatype().getId()).getSingleResult());



        //TODO: Set all Authors and Topics
        //set authors in publication


        //publication.setAuthor(entityManager.find(Genre.class, publicationDTO.getGenre_id()));
        entityManager.persist(publication);

        return publication;
    }
}
