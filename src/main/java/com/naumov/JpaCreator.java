package com.naumov;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class JpaCreator {
    public static void main(String[] args) {
        Address ourAddressInTbilisi = new Address();
        ourAddressInTbilisi.setMoveInDate(LocalDate.of(2022, 4, 21));
        ourAddressInTbilisi.setCity("Tbilisi");
        ourAddressInTbilisi.setStreet("Navtlugi");
        ourAddressInTbilisi.setHouse("10");
        ourAddressInTbilisi.setApt(553);

        Person semyon = new Person();
        semyon.setName("Semyon");
        semyon.setAge(26);
        semyon.setSsn("1234567890");
        semyon.setDateOfBirth(LocalDate.of(1996, 3, 5));
        semyon.setSex(Sex.MALE);
        semyon.setAddress(ourAddressInTbilisi);

        ourAddressInTbilisi.setOwner(semyon);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(semyon);
        entityManager.persist(ourAddressInTbilisi);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
