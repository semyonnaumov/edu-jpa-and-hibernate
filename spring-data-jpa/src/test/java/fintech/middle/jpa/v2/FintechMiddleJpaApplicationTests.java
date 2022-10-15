package fintech.middle.jpa.v2;

import fintech.middle.jpa.v2.model.CourseV2;
import fintech.middle.jpa.v2.model.StudentV2;
import fintech.middle.jpa.v2.repository.CourseRepositoryV2;
import fintech.middle.jpa.v2.repository.StudentRepositoryV2;
import fintech.middle.jpa.v2.service.CourseServiceV2;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FintechMiddleJpaApplicationTests {
    @Autowired
    private CourseRepositoryV2 courseRepository;
    @Autowired
    private StudentRepositoryV2 studentRepository;
    @Autowired
    private CourseServiceV2 courseService;

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
        CourseV2 savedCourse = courseRepository.findById(courseId).get();
        savedCourse.setCourseName("Other random name");
        courseRepository.save(savedCourse);
        courseRepository.flush(); // nothing happens here actually
    }

    @Test
    @Transactional
    void o2m_and_cascade() {
        Long courseId = courseService.prepareCourse();
        CourseV2 savedCourse = courseRepository.findById(courseId).get();

        assertThat(savedCourse.getStudentsApplied().size()).isEqualTo(2);
        System.out.println(savedCourse.getStudentsApplied());

        courseRepository.delete(savedCourse);
        assertThat(studentRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    @Transactional
    void lazy_loading() {
        Long courseId = courseService.prepareCourse();
        CourseV2 savedCourse = courseRepository.findById(courseId).get();

        System.out.println("Loaded course " + courseId);
        System.out.println(savedCourse.getStudentsApplied());
    }

    // can be fixed with @Transactional, EAGER loading or other variants
    @Test
    void lazy_init_exception() {
        Long courseId = courseService.prepareCourse();
        CourseV2 savedCourse = courseRepository.findById(courseId).get();

        System.out.println("Loaded course " + courseId);
        assertThrows(LazyInitializationException.class, () -> {
            List<StudentV2> studentsApplied = savedCourse.getStudentsApplied();
            System.out.println(studentsApplied);
        });
    }

    // invokes 4 select queries
    @Test
    @Transactional
    void n_plus_one() {
        courseService.prepareCourses();
        List<CourseV2> savedCourses = courseRepository.findAll(); // returns 3 courses

        savedCourses.forEach(course -> {
            System.out.println("Loaded course " + course.getId());
            System.out.println(course.getStudentsApplied());
        });
    }

    // invokes 1 select query
    @Test
    @Transactional
    void fetch_graph() {
        courseService.prepareCourses();
        List<CourseV2> savedCourses = courseRepository.findAllUsingEntityGraph(); // returns 3 courses

        savedCourses.forEach(course -> {
            System.out.println("Loaded course " + course.getId());
            System.out.println(course.getStudentsApplied());
        });
    }
}