package com.naumov;

import com.naumov.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaDeleter {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee semyon = entityManager.find(Employee.class, 1);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(semyon);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
