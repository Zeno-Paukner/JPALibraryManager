package at.htlleonding.Logic;

import at.htlleonding.DTOs.GenreDTO;
import at.htlleonding.persistence.Genre;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
@ApplicationScoped
public class GenreLogic {

    @Inject
    EntityManager entityManager;

    @Transactional
    public Integer createGenre(GenreDTO genreDTO) {
        Genre genre = new Genre();
        for (Genre genre1 : entityManager.createQuery("SELECT g FROM Genre g", Genre.class).getResultList()) {
            if (genre1.getGenre().equals(genreDTO.getGenre())) {
                throw new RuntimeException("Genre already exists");
            }
        }
        genre.setGenre(genreDTO.getGenre());
        entityManager.persist(genre);
        return genre.getId();
    }
}
