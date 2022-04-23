package com.naumov;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "move_in_date")
    private LocalDate moveInDate;
    private String city;
    private String street;
    private String house;
    private Integer apt;
    @OneToOne(mappedBy = "address")
    private Person owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getMoveInDate() {
        return moveInDate;
    }

    public void setMoveInDate(LocalDate moveInDate) {
        this.moveInDate = moveInDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Integer getApt() {
        return apt;
    }

    public void setApt(Integer apt) {
        this.apt = apt;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", moveInDate=" + moveInDate +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", apt=" + apt +
                '}';
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
