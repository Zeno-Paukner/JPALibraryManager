package at.htlleonding.DTOs;


import at.htlleonding.persistence.Copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SaleDTO {

    private List<Integer> copy_id = new ArrayList<>();

    private Integer client_id;

    private Integer employee_id;

    public Collection<? extends Copy> getCopy_id() {
        return copy_id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }
}
