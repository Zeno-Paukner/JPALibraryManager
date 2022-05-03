package at.htlleonding.DTOs;

import at.htlleonding.persistence.Sale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InvoiceDTO implements Serializable {
    private final ClientDTO client;
    private final EmployeeDTO employee;
    private final List<SaleDTO> sales = new ArrayList<>();


    public InvoiceDTO(ClientDTO client, EmployeeDTO employee, List<SaleDTO> sales) {
        this.client = client;
        this.employee = employee;
        this.sales.addAll(sales);
    }

    public ClientDTO getClient() {
        return client;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public List<SaleDTO> getSales() {
        return sales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceDTO entity = (InvoiceDTO) o;
        return Objects.equals(this.client, entity.client) &&
                Objects.equals(this.employee, entity.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, employee);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "client = " + client + ", " +
                "employee = " + employee + ")";
    }
}
