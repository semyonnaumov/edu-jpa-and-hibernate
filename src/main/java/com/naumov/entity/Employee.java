package com.naumov.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirthNew) {
        this.dateOfBirth = dateOfBirthNew;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public AccessCard getAccessCard() {
        return accessCard;
    }

    public void setAccessCard(AccessCard accessCard) {
        this.accessCard = accessCard;
    }

    public List<PayStub> getPayStubs() {
        return payStubs;
    }

    public void setPayStubs(List<PayStub> payStubs) {
        this.payStubs = payStubs;
    }

    public void addPayStub(PayStub payStub) {
        this.payStubs.add(payStub);
    }

    public List<EmailGroup> getEmailGroups() {
        return emailGroups;
    }

    public void setEmailGroups(List<EmailGroup> emailGroups) {
        this.emailGroups = emailGroups;
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
