package at.htlleonding.DTOs;

import at.htlleonding.persistence.Client;
import at.htlleonding.persistence.Copy;
import at.htlleonding.persistence.Employee;

public class RentDTO {

    private CopyDTO copyDTO;

    private EmployeeDTO employeeDTO;

    private ClientDTO clientDTO;

    public RentDTO(Copy copy, Employee employee, Client client) {
        this.copy = copy;
        this.employee = employee;
        this.client = client;
    }


}
