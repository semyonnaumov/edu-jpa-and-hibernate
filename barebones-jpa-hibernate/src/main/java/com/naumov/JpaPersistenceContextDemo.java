package com.naumov;

import com.naumov.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class JpaPersistenceContextDemo {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("Vasya");
        employee.setAge(45);
        employee.setSalary(30_000);
        employee.setDateOfBirth(LocalDate.now());
        employee.setSex(Employee.Sex.MALE);
        System.out.println("--------------- Employee created");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager manager = factory.createEntityManager();
        System.out.println("--------------- EntityManager created");

        EntityTransaction transaction = manager.getTransaction();
        System.out.println("--------------- EntityTransaction created");

        transaction.begin();
        System.out.println("--------------- EntityTransaction begin");
        manager.persist(employee);
        System.out.println("--------------- Employee persisted");
        Employee employeeFound = manager.find(Employee.class, 1);
        System.out.println("--------------- Employee found: " + employeeFound);
        System.out.println("--------------- ref equality: " + (employeeFound == employee));
        transaction.commit();
        System.out.println("--------------- EntityTransaction commit");

        manager.close();
        factory.close();
    }
}
