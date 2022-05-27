package at.htlleonding.persistence;
import javax.persistence.*;
import java.util.Date;

//Create a new Entity called Rent with the following attributes:
//Primery Key: Id
//Forain Key: CopyId
//Forain Key: EmployeeId
//Column with date: StartDate
//Column with date: EndDate
//Column with date: Deadline

@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private Date deadline;

    @Column
    private int prolongedCounter = 0;



    public Rent(Copy copy, Employee employee, Client client, Date startDate, Date endDate, Date deadline) {
        this.copy = copy;
        this.employee = employee;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadline = deadline;
    }

    public Rent() {
    }

    public int getId() {
        return id;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProlongedCounter() {
        return prolongedCounter;
    }

    public void setProlongedCounter(int prolongedCounter) {
        this.prolongedCounter = prolongedCounter;
    }
}

