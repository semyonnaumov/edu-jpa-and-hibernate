package fintech.middle.jpa.v1.repository;

import fintech.middle.jpa.v1.model.StudentV1;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StudentRepositoryV1 extends JpaRepository<StudentV1, Long> {
    StudentV1 findByName(String name);

    StudentV1 findByNameAndCreationDateBeforeOrderByName(String name, LocalDateTime creationDate);

    List<StudentV1> findByStatusInOrNameStartsWith(List<StudentV1.Status> statuses, String nameStart);

    List<StudentV1> findByNameContaining(String namePart, Pageable pageable);
}
