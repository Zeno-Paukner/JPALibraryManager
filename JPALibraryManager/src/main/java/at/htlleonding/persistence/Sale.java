package at.htlleonding.persistence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "sale")
    private ArrayList<Copy> copyList = new ArrayList<Copy>();

    @Column
    private Date saleDate;

    @Column
    private double amount;



}
