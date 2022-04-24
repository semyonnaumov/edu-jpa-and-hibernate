package com.naumov;

import com.naumov.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaReader {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Employee employee = entityManager.find(Employee.class, 1);
        System.out.println(employee);

        entityManager.close();
        entityManagerFactory.close();
    }
}
