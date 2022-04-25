package at.htlleonding.DTOs;

public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private Integer salary;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String firstName, String lastName, Integer salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getSalary() {
        return salary;
    }
}
