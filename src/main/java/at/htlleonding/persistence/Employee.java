package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee extends Person {

    @Column
    private String salary;

    @OneToMany(mappedBy = "employee", cascade= CascadeType.ALL)
    private List<Rent> rents = new ArrayList<>();

    @OneToMany (mappedBy = "employee", cascade=CascadeType.ALL)
    private List<Sale> sales = new ArrayList<>();

    public Employee(String firstName, String lastName, String salary) {
        super(firstName, lastName);
        this.salary = salary;
    }

    public Employee(String salary) {
        this.salary = salary;
    }

    public Employee() {

    }

    public List<Rent> getRents() {
        return rents;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
