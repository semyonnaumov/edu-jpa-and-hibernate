package fintech.middle.jpa.v1.repository;

import fintech.middle.jpa.v1.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String name);

    Student findByNameAndCreationDateBeforeOrderByName(String name, LocalDateTime creationDate);

    List<Student> findByStatusInOrNameStartsWith(List<Student.Status> statuses, String nameStart);

    List<Student> findByNameContaining(String namePart, Pageable pageable);
}
