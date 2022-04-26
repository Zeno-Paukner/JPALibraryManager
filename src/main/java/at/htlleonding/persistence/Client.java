package at.htlleonding.persistence;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends Person{

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @OneToMany (mappedBy = "client", cascade= CascadeType.ALL)
    private final List<Sale> sales = new ArrayList<>();

    @OneToMany (mappedBy = "client", cascade=CascadeType.ALL)
    private final List<Rent> rents = new ArrayList<>();

    @OneToMany (mappedBy = "client", cascade=CascadeType.ALL)
    private final List<Reservation> reservations = new ArrayList<>();

<<<<<<< Updated upstream
    public Client(String firstName, String lastName, String phoneNumber, String email) {
=======

    public Client(String firstName, String lastName, String email, String phoneNumber ) {
>>>>>>> Stashed changes
        super(firstName, lastName);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Client() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
