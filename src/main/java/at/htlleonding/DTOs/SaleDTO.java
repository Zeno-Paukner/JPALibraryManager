package at.htlleonding.DTOs;


import java.util.ArrayList;
import java.util.List;

public class SaleDTO {

    private List<Integer> copy_id = new ArrayList<>();

    private Integer client_id;

    private Integer employee_id;

    private Double totalPrice;

    public List<Integer> getCopy_ids() {
        return copy_id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }




}
