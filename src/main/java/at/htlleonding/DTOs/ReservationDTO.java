package at.htlleonding.DTOs;

import java.io.Serializable;
import java.util.Objects;

public class ReservationDTO implements Serializable {
    private final ClientDTO client;
    private final PublicationDTO publication;

    public ReservationDTO(ClientDTO client, PublicationDTO publication) {
        this.client = client;
        this.publication = publication;
    }

    public ClientDTO getClient() {
        return client;
    }

    public PublicationDTO getPublication() {
        return publication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDTO entity = (ReservationDTO) o;
        return Objects.equals(this.client, entity.client) &&
                Objects.equals(this.publication, entity.publication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, publication);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "client = " + client + ", " +
                "publication = " + publication + ")";
    }
}
