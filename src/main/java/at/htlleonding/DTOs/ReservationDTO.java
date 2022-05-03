package at.htlleonding.DTOs;

import at.htlleonding.persistence.Client;
import at.htlleonding.persistence.Publication;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ReservationDTO {
    private Client client;

    private Publication publication;

    public Client getClient() {
        return client;
    }

    public Publication getPublication() {
        return publication;
    }
}
