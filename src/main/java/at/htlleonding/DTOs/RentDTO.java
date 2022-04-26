package at.htlleonding.DTOs;

import at.htlleonding.persistence.Client;
import at.htlleonding.persistence.Copy;
import at.htlleonding.persistence.Employee;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class RentDTO {

    private Integer copy_id;

    private Integer employee_id;


    private Integer client_id;

    public Integer getCopy_id() {
        return copy_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setCopy_id(Integer copy_id) {
        this.copy_id = copy_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }


}
