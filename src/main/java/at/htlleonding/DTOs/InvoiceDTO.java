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

    private Integer client_id;


    private Integer employee_id;


    private final List<Integer> sale_ids = new ArrayList<>();

    public Integer getClient_id() {
        return client_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public List<Integer> getSale_ids() {
        return sale_ids;
    }
}
