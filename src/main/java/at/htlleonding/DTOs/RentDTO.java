package at.htlleonding.DTOs;

import java.io.Serializable;
import java.util.Objects;

public class RentDTO implements Serializable {
    private final CopyDTO copy;
    private final EmployeeDTO employee;
    private final ClientDTO client;
    private final Integer id;

    public RentDTO(CopyDTO copy, EmployeeDTO employee, ClientDTO client) {
        this.copy = copy;
        this.employee = employee;
        this.client = client;
        this.id = 0;
    }

    public RentDTO(CopyDTO copy, EmployeeDTO employee, ClientDTO client, Integer id) {
        this.copy = copy;
        this.employee = employee;
        this.client = client;
        this.id = id;
    }

    public CopyDTO getCopy() {
        return copy;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public ClientDTO getClient() {
        return client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentDTO entity = (RentDTO) o;
        return Objects.equals(this.copy, entity.copy) &&
                Objects.equals(this.employee, entity.employee) &&
                Objects.equals(this.client, entity.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(copy, employee, client);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "copy = " + copy + ", " +
                "employee = " + employee + ", " +
                "client = " + client + ")";
    }

    public Integer getId() {
        return id;
    }
}
