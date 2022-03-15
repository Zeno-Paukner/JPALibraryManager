package at.htlleonding.persistence;
import javax.persistence.*;

//Create a new Entity called Rent with the following attributes:
//Primery Key: Id
//Forain Key: CopyId
//Forain Key: EmployeeId
//Column with date: StartDate
//Column with date: EndDate
//Column with date: Deadline

@Entity
@Table(name = "Rent")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String startDate;
    private String endDate;
    private String deadline;

    public Rent() {
    }

    public Rent(Copy copy, Employee employee, String startDate, String endDate, String deadline) {
        this.copy = copy;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadline = deadline;
    }


    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}

