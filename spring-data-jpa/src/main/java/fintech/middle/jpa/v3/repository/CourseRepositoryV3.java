package fintech.middle.jpa.v3.repository;

import fintech.middle.jpa.v3.model.CourseV3;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepositoryV3 extends JpaRepository<CourseV3, Long> {
    @Query("from v3Courses c join fetch c.studentsApplied")
    List<CourseV3> loadAllFetchStudents(Pageable pageable);
}
