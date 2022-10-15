package fintech.middle.jpa.v2.repository;

import fintech.middle.jpa.v2.model.CourseV2;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepositoryV2 extends JpaRepository<CourseV2, Long> {
    @Query("from v2Courses c join fetch c.studentsApplied")
    List<CourseV2> loadAllFetchStudents();

    @Query("from v2Courses")
    @EntityGraph(attributePaths = "studentsApplied", type = EntityGraph.EntityGraphType.LOAD)
    List<CourseV2> findAllUsingEntityGraph();
}
