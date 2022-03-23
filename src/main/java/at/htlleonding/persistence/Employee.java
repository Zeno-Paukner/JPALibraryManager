package at.htlleonding.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Employee extends Person {

    @Column
    private String salary;

    public String getSalary(){return salary;}
    public void setSalary(String salary){this.salary=salary;}
}
