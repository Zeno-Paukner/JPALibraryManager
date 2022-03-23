package at.htlleonding.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee extends Person {

    @Column
    private String salary;

    @OneToMany(mappedBy = "rent")
    private List<Rent> rents = new ArrayList<>();

    @OneToMany (mappedBy = "client")
    private List<Sale> sales = new ArrayList<>();

    public Employee(String firstName, String lastName, String phoneNumber, Integer id, String salary) {
        super(firstName, lastName, phoneNumber);
        this.salary = salary;
    }

    public Employee(Integer id, String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
