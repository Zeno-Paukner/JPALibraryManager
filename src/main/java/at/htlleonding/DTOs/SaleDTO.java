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




}
