package fintech.middle.jpa.v4.repository;

import fintech.middle.jpa.v4.model.StudentV4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositoryV4 extends JpaRepository<StudentV4, Long>, RevisionRepository<StudentV4, Long, Long> {
}
