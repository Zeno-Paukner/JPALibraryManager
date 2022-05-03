package at.htlleonding.DTOs;

import at.htlleonding.persistence.Client;
import at.htlleonding.persistence.Employee;
import at.htlleonding.persistence.Sale;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDTO {

    private Client client;

    private Employee employee;

    private List<Sale> sale = new ArrayList<>();

    public Client getClient() {
        return client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public List<Sale> getSale() {
        return sale;
    }

    public InvoiceDTO(Client client, Employee employee, List<Sale> sale) {
        this.client = client;
        this.employee = employee;
        this.sale = sale;
    }

    public void setSale(List<Sale> sale) {
        this.sale = sale;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEmployee(Employee employee) {this.employee = employee;}

    public InvoiceDTO() {
    }
}
