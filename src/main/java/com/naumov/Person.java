package com.naumov;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "people")
public class Person {
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
    @OneToOne
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
                ", address=" + address +
                '}';
    }
}
