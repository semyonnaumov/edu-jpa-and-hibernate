package com.naumov;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaReader {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Address person = entityManager.find(Address.class, 2);
        System.out.println(person);

        entityManager.close();
        entityManagerFactory.close();
    }
}
