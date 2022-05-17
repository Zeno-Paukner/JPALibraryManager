package at.htlleonding.DTOs;

import java.io.Serializable;
import java.util.Objects;

public class GenreDTO implements Serializable {
    private final String genre;
    private final Integer id;

    public GenreDTO(String genre, Integer id) {
        this.genre = genre;
        this.id = id;
    }
    public GenreDTO(String genre) {
        this.genre = genre;
        this.id = 0;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDTO entity = (GenreDTO) o;
        return Objects.equals(this.genre, entity.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "genre = " + genre + ")";
    }

    public Integer getId() {
        return id;
    }
}
