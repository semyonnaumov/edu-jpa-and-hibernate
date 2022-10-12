package com.naumov;

import com.naumov.entity.EmailGroup;
import com.naumov.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaUpdater {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee semyon = entityManager.find(Employee.class, 1);
        EmailGroup group2 = entityManager.find(EmailGroup.class, 8);

        semyon.addEmailGroups(group2);
        group2.addEmployee(semyon);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(semyon);
        entityManager.persist(group2);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
