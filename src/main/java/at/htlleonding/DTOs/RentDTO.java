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
}
