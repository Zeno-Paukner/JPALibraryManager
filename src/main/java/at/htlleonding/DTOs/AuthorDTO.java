package at.htlleonding.DTOs;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AuthorDTO implements Serializable {
    private final String firstName;
    private final String lastName;
    private final List<PublicationDTO> publications;

    public AuthorDTO(String firstName, String lastName, List<PublicationDTO> publications) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.publications = publications;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<PublicationDTO> getPublications() {
        return publications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO entity = (AuthorDTO) o;
        return Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.publications, entity.publications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, publications);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "publications = " + publications + ")";
    }
}
