package com.naumov;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class JpaStarterMain {
    public static void main(String[] args) {
        Person semyon = new Person();
        semyon.setName("Semyon");
        semyon.setAge(26);
        semyon.setSsn("1234567890");
        semyon.setDateOfBirth(LocalDate.of(1996, 3, 5));
        semyon.setSex(Sex.MALE);

        Person yana = new Person();
        yana.setName("Yana");
        yana.setAge(25);
        yana.setSsn("1234567891");
        yana.setDateOfBirth(LocalDate.of(1996, 6, 28));
        yana.setSex(Sex.FEMALE);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(semyon);
        entityManager.persist(yana);
        transaction.commit();
    }
}
