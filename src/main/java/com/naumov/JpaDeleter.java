package com.naumov;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaDeleter {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Person person = entityManager.find(Person.class, 2);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(person);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
