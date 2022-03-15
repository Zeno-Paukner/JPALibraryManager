package at.htlleonding.persistence;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Client extends Person{

    @Column
    private String phoneNumber;

    @Column
    private String email;

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail () {
        return email;
    }

    public void setPhonNumber(String phoneNumber){this.phoneNumber=phoneNumber; }
    public void setEmail(String email){this.email=email; }

}
