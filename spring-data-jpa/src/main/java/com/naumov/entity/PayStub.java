package com.naumov.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
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
}
