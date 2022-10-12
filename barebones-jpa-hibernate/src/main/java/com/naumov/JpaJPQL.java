package com.naumov;

import com.naumov.entity.Employee;

import javax.persistence.*;
import java.util.List;

public class JpaJPQL {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager manager = factory.createEntityManager();

        // simple query
        int age = 25;
        TypedQuery<Employee> query = manager.createQuery(
                "SELECT e FROM Employee e WHERE e.age > :age",
                Employee.class
        ).setParameter("age", age);

        List<Employee> employees = query.getResultList();
        System.out.println(employees);

        // named query
        TypedQuery<Employee> namedQuery = manager.createNamedQuery("employeesOrderedByAgeAsc", Employee.class);
        System.out.println(namedQuery.getResultList());

        manager.close();
        factory.close();
    }
}
