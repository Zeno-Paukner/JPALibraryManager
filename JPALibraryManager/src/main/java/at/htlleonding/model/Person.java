package at.htlleonding.model;

import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Person")
@Cacheable
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    public String getFirstName() {
        return FirstName;
    }

    @Column(length = 50)
    private String FirstName;
    @Column(length = 50)
    private String LastName;
    @Column
    private LocalDate dob;

    public Person() {
    }

    public Person(String firstName, String lastName, LocalDate dob) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.dob = dob;
    }

    public Integer getId() {
        return Id;
    }

    public String getFullName() {
        return FirstName + " " + LastName;
    }

    @Override
    public String toString() { return Id + " " + getFullName(); }
}
