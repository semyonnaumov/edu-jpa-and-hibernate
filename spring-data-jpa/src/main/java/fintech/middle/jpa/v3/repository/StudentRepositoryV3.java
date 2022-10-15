package fintech.middle.jpa.v3.repository;

import fintech.middle.jpa.v3.model.NamedEntity;
import fintech.middle.jpa.v3.model.StudentV3;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepositoryV3 extends JpaRepository<StudentV3, Long> {
    @Query("update v3Students s set s.name=:name where s.id=:id")
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    int updateStudentName(Long id, String name);

    @Query("select s.name from v3Students s where s.course.courseName=:courseName")
    List<String> findAllStudentNamesAttendingCourse(String courseName);

    List<NamedEntity> findByCourse_CourseName(String name);

    <T> List<T> findStudentsByName(String name, Class<T> viewClass);

    @Query("from v3Students s join s.course c where c.courseName=:courseName")
    List<StudentV3> findAllStudentsAttendingCourse(String courseName, Pageable page);
}
