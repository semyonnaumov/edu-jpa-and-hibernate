package com.naumov;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

public class JpaJPQL {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myApp");
        EntityManager manager = factory.createEntityManager();

        TypedQuery<Object[]> query = manager.createQuery(
                "SELECT e.name, e.age, ac.issueDate FROM Employee e, AccessCard ac " +
                        "WHERE e.accessCard.id = ac.id",
                Object[].class
        );
        List<Object[]> employeeData = query.getResultList();

        for (Object[] employeeDatum : employeeData) {
            System.out.println(Arrays.toString(employeeDatum));
        }

        manager.close();
        factory.close();
    }
}
