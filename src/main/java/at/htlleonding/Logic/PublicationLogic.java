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

        publication.setPublisher(entityManager.find(Publisher.class, publicationDTO.getPublisher()));
        publication.setLanguage(entityManager.find(Language.class, publicationDTO.getLanguage()));
        publication.setTranslated(publicationDTO.getIsTranslated());
        publication.setMediatype(entityManager.find(Mediatype.class, publicationDTO.getMediatype()));
        //set genre in publication
        publication.setGenre(entityManager.find(Genre.class, publicationDTO.getGenre()));
        publication.setGenre(entityManager.find(Genre.class, publicationDTO.getGenre()));

        publication.setPublisher(entityManager.find(Publisher.class, publicationDTO));
        publication.setLanguage(entityManager.find(Language.class, publicationDTO));
        publication.setTranslated(publicationDTO.getIsTranslated());
        publication.setMediatype(entityManager.find(Mediatype.class, publicationDTO));
        //set genre in publication
        publication.setGenre(entityManager.find(Genre.class, publicationDTO));
        publication.setGenre(entityManager.find(Genre.class, publicationDTO));


        //TODO: Set all Authors and Topics
        //set authors in publication


        //publication.setAuthor(entityManager.find(Genre.class, publicationDTO.getGenre_id()));
        entityManager.persist(publication);

        return publication;
    }
}
