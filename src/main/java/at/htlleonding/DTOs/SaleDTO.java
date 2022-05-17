package at.htlleonding.DTOs;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SaleDTO implements Serializable {
    private final List<CopyDTO> copyList;
    private final ClientDTO client;
    private final EmployeeDTO employee;
    private final int totalPrice;

    public SaleDTO(List<CopyDTO> copyList, ClientDTO client, EmployeeDTO employee) {
        this.copyList = copyList;
        this.client = client;
        this.employee = employee;
    }

    public List<CopyDTO> getCopyList() {
        return copyList;
    }

    public ClientDTO getClient() {
        return client;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleDTO entity = (SaleDTO) o;
        return Objects.equals(this.copyList, entity.copyList) &&
                Objects.equals(this.client, entity.client) &&
                Objects.equals(this.employee, entity.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(copyList, client, employee);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "copyList = " + copyList + ", " +
                "client = " + client + ", " +
                "employee = " + employee + ")";
    }
}
