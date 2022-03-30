package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee extends Person {

    @Column
    private Integer salary;

    @OneToMany(mappedBy = "employee", cascade= CascadeType.ALL)
    private final List<Rent> rents = new ArrayList<>();

    @OneToMany (mappedBy = "employee", cascade=CascadeType.ALL)
    private final List<Sale> sales = new ArrayList<>();

    public Employee(String firstName, String lastName, Integer salary) {
        super(firstName, lastName);
        this.salary = salary;
    }

    public Employee(Integer salary) {
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
