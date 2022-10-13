package fintech.middle.jpa.v2;

import fintech.middle.jpa.v2.model.Course;
import fintech.middle.jpa.v2.repository.CourseRepository;
import fintech.middle.jpa.v2.repository.StudentRepositoryV2;
import fintech.middle.jpa.v2.service.CourseService;
import fintech.middle.jpa.v2.service.MathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FintechMiddleJpaApplicationTests {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepositoryV2 studentRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private MathService mathService;

    @BeforeEach
    void cleanup_tables() {
        courseRepository.deleteAll();
        studentRepository.deleteAll();
    }

    @Test
    @Transactional
    void dirty_checking() {
        var courseId = courseService.changeCourseName();
        assertThat(courseRepository.getById(courseId).getCourseName()).isEqualTo("Mathematics");
    }

    // this transaction will not generate any inserts/updates/deletes on the DB
    @Test
    @Transactional(readOnly = true)
    void readonly_transaction() {
        Long courseId = courseService.prepareCourse();
        Course savedCourse = courseRepository.getById(courseId);
        savedCourse.setCourseName("Other random name");
        courseRepository.save(savedCourse);
        courseRepository.flush(); // nothing happens here actually
    }

    @Test
    @Transactional
    void o2m_and_cascade() {
        Long courseId = courseService.prepareCourse();
        Course savedCourse = courseRepository.getById(courseId);

        assertThat(savedCourse.getStudentsApplied().size()).isEqualTo(2);
        System.out.println(savedCourse.getStudentsApplied());

        courseRepository.delete(savedCourse);
        assertThat(studentRepository.findAll().size()).isEqualTo(0);
    }
}