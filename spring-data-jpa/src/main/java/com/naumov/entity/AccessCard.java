package com.naumov.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "access_cards")
public class AccessCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @OneToOne(mappedBy = "accessCard")
    private Employee owner;
}
