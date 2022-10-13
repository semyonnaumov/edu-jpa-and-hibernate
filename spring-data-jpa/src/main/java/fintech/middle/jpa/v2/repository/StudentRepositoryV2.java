package fintech.middle.jpa.v2.repository;

import fintech.middle.jpa.v2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositoryV2 extends JpaRepository<Student, Long> {
}
