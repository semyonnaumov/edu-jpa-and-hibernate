package com.naumov.entity;

import javax.persistence.*;
import java.time.LocalDate;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate moveInDate) {
        this.issueDate = moveInDate;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", moveInDate=" + issueDate +
                '}';
    }
}
