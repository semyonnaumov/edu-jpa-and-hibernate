package com.naumov;

import com.naumov.entity.AccessCard;
import com.naumov.entity.Employee;
import com.naumov.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class SpringBootApp implements ApplicationListener<ContextRefreshedEvent> {
    private EntityRepository entityRepository;

    @Autowired
    public SpringBootApp(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        AccessCard semyonCard = new AccessCard();
//        semyonCard.setIssueDate(LocalDate.of(2022, 4, 21));
//
//        Employee semyon = new Employee();
//        semyon.setName("Semyon");
//        semyon.setAge(26);
//        semyon.setSsn("1234567890");
//        semyon.setDateOfBirth(LocalDate.of(1996, 3, 5));
//        semyon.setSex(Employee.Sex.MALE);
//        semyon.setAccessCard(semyonCard);
//        semyonCard.setOwner(semyon);

        Optional<Employee> employee = entityRepository.findById(1);
        employee.ifPresent((e) -> System.out.println(employee.get()));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApp.class, args);
    }
}
