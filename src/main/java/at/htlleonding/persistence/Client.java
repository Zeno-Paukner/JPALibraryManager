package at.htlleonding.persistence;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends Person{

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @OneToMany (mappedBy = "client")
    private List<Sale> sales = new ArrayList<>();

    @OneToMany (mappedBy = "rent")
    private List<Rent> rents = new ArrayList<>();

    @OneToMany (mappedBy = "reservation")
    private List<Reservation> reservations = new ArrayList<>();


    public Client(String firstName, String lastName, String phoneNumber, Integer id, String phoneNumber1, String email) {
        super(firstName, lastName, phoneNumber);
        this.phoneNumber = phoneNumber1;
        this.email = email;
    }

    public Client(Integer id, String phoneNumber, String email) {
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
