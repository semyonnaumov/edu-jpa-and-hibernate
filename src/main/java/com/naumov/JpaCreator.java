package com.naumov;

import com.naumov.entity.AccessCard;
import com.naumov.entity.EmailGroup;
import com.naumov.entity.PayStub;
import com.naumov.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class JpaCreator {
    public static void main(String[] args) {
        AccessCard semyonCard = new AccessCard();
        semyonCard.setIssueDate(LocalDate.of(2022, 4, 21));

        AccessCard yanaCard = new AccessCard();
        yanaCard.setIssueDate(LocalDate.of(2021, 3, 15));

        Employee semyon = new Employee();
        semyon.setName("Semyon");
        semyon.setAge(26);
        semyon.setSsn("1234567890");
        semyon.setDateOfBirth(LocalDate.of(1996, 3, 5));
        semyon.setSex(Employee.Sex.MALE);
        semyon.setAccessCard(semyonCard);
        semyonCard.setOwner(semyon);

        Employee yana = new Employee();
        yana.setName("Yana");
        yana.setAge(25);
        yana.setSsn("1234567891");
        yana.setDateOfBirth(LocalDate.of(1996, 6, 28));
        yana.setSex(Employee.Sex.FEMALE);
        yana.setAccessCard(yanaCard);
        yanaCard.setOwner(yana);

        PayStub payStub1 = new PayStub();
        payStub1.setPayPeriodStart(LocalDate.of(2018, 8, 21));
        payStub1.setPayPeriodEnd(LocalDate.of(2022, 3, 18));
        payStub1.setSalary(180_000);
        payStub1.setOwner(semyon);
        semyon.addPayStub(payStub1);

        PayStub payStub2 = new PayStub();
        payStub2.setPayPeriodStart(LocalDate.of(2022, 5, 9));
        payStub2.setPayPeriodEnd(LocalDate.of(2023, 5, 9));
        payStub2.setSalary(240_000);
        payStub2.setOwner(semyon);
        semyon.addPayStub(payStub2);

        EmailGroup emailGroup1 = new EmailGroup();
        emailGroup1.setName("Company watercooler discussions");
        emailGroup1.addEmployee(semyon);
        semyon.addEmailGroups(emailGroup1);
        emailGroup1.addEmployee(yana);
        yana.addEmailGroups(emailGroup1);

        EmailGroup emailGroup2 = new EmailGroup();
        emailGroup2.setName("Corporative place discussions");
        emailGroup2.addEmployee(yana);
        yana.addEmailGroups(emailGroup2);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(semyon);
        entityManager.persist(semyonCard);
        entityManager.persist(yana);
        entityManager.persist(yanaCard);
        entityManager.persist(payStub1);
        entityManager.persist(payStub2);
        entityManager.persist(emailGroup1);
        entityManager.persist(emailGroup2);
        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
