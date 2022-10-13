package com.naumov.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "employees")
@NamedQuery(
        name = "employeesOrderedByAgeAsc",
        query = "SELECT e FROM Employee e ORDER BY e.age ASC"
)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(unique = true, length = 10)
    private String ssn;
    @Column(name = "date_of_birth", updatable = false)
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private Double salary;
    @OneToOne(cascade = CascadeType.REMOVE)
    private AccessCard accessCard;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.REMOVE)
    private List<PayStub> payStubs = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "email_group_subscriptions",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<EmailGroup> emailGroups = new ArrayList<>();

    @Transient
    private String debugInfo;
    private transient String moreDebugInfo;

    public void addPayStub(PayStub payStub) {
        this.payStubs.add(payStub);
    }

    public void addEmailGroups(EmailGroup emailGroup) {
        this.emailGroups.add(emailGroup);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", ssn='" + ssn + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", sex=" + sex +
                ", salary=" + salary +
                ", address=" + accessCard +
                '}';
    }

    public enum Sex {
        MALE,
        FEMALE
    }
}
