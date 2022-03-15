package at.htlleonding.persistence;



//Create a new Entity called Reservation with
//the following attributes:
//Primary Key: Id
//Foreign Key: PublicationId
//A Date Column; ReservationDate
//one-to-many: ClientId

import javax.persistence.*;
import java.util.Date;




@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
/*
    @ManyToOne
    private

    @Column
    private Date ReservationDate;
*/
    @ManyToOne
    private Client ClientId;


}


