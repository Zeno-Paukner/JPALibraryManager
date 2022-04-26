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

    private List<Integer> sale_ids = new ArrayList<>();

    public Integer getClient_id() {
        return client_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public List<Integer> getSale_ids() {
        return sale_ids;
    }

    public InvoiceDTO(Integer client_id, Integer employee_id, List<Integer> sale_ids) {
        this.client_id = client_id;
        this.employee_id = employee_id;
        this.sale_ids = sale_ids;
    }

    public void setSale_ids(List<Integer> sale_ids) {
        this.sale_ids = sale_ids;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public InvoiceDTO() {
    }
}
