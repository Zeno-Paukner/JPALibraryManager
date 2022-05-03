package at.htlleonding.DTOs;

import at.htlleonding.persistence.Client;
import at.htlleonding.persistence.Copy;
import at.htlleonding.persistence.Employee;

public class RentDTO {

    private Integer copy;

    private Employee employee;


    private Client client;

    public Integer getCopy() {
        return copy;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Client getClient() {return client;}

    public void setCopy(Integer copy) {this.copy = copy;}

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}
