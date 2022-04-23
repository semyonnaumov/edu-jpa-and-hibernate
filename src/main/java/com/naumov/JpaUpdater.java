package com.naumov;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaUpdater {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Person yana = entityManager.find(Person.class, 2);
        yana.setSalary(200_000);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(yana);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
