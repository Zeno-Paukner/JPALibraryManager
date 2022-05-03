package at.htlleonding.DTOs;


import at.htlleonding.persistence.Client;
import at.htlleonding.persistence.Copy;
import at.htlleonding.persistence.Employee;

import java.util.ArrayList;
import java.util.List;

public class SaleDTO {

    private List<Integer> copy = new ArrayList<>();

    private Client client;

    private Employee employee;

    private Double totalPrice;

    public List<Integer> getCopy() {
        return copy;
    }

    public Client getClient() {
        return client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public SaleDTO(List<Integer> copy_id, Integer client_id, Integer employee_id, Double totalPrice) {
        this.copy_id = copy_id;
        this.client_id = client_id;
        this.employee_id = employee_id;
        this.totalPrice = totalPrice;
    }

    public SaleDTO() {

    }

    public void setCopy_id(List<Integer> copy_id) {
        this.copy_id = copy_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
