package at.htlleonding.persistence;




import org.graalvm.polyglot.HostAccess;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
/*
    @ManyToOne
    private
*/
    @Column
    private Date reservationDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    public Reservation(Date _reservationDate, Client _client) {
        reservationDate = _reservationDate;
        client = _client;
    }

    public Reservation() {

    }
    public Integer getId() {
        return id;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        reservationDate = reservationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClientId(Client _client) {
        client = _client;
    }
}


