package at.htlleonding.DTOs;

import at.htlleonding.persistence.Client;
import at.htlleonding.persistence.Publication;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ReservationDTO {
    private Integer client_id;

    private Integer publication_id;

    public Integer getClient_id() {
        return client_id;
    }

    public Integer getPublication_id() {
        return publication_id;
    }
}
