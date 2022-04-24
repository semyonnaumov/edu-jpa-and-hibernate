package com.naumov.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pay_stubs")
public class PayStub {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "pay_period_start")
    private LocalDate payPeriodStart;
    @Column(name = "pay_period_end")
    private LocalDate payPeriodEnd;
    private float salary;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Employee owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getPayPeriodStart() {
        return payPeriodStart;
    }

    public void setPayPeriodStart(LocalDate payPeriodStart) {
        this.payPeriodStart = payPeriodStart;
    }

    public LocalDate getPayPeriodEnd() {
        return payPeriodEnd;
    }

    public void setPayPeriodEnd(LocalDate payPeriodEnd) {
        this.payPeriodEnd = payPeriodEnd;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "PayStub{" +
                "id=" + id +
                ", payPeriodStart=" + payPeriodStart +
                ", payPeriodEnd=" + payPeriodEnd +
                ", salary=" + salary +
                ", owner=" + owner +
                '}';
    }
}
