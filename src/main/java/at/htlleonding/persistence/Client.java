package at.htlleonding.persistence;


import at.htlleonding.DTOs.ClientDTO;

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

    public Client(String firstName, String lastName, String email, String phoneNumber ) {
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


