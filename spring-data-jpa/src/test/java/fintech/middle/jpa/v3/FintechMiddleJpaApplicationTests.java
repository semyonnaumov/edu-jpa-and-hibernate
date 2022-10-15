package fintech.middle.jpa.v3;

import fintech.middle.jpa.v3.model.*;
import fintech.middle.jpa.v3.repository.CourseRepositoryV3;
import fintech.middle.jpa.v3.repository.StudentRepositoryV3;
import fintech.middle.jpa.v3.service.CoursePlanningServiceV3;
import fintech.middle.jpa.v3.service.CourseServiceV3;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FintechMiddleJpaApplicationTests {
    @Autowired
    private CourseRepositoryV3 courseRepository;
    @Autowired
    private StudentRepositoryV3 studentRepository;
    @Autowired
    private CoursePlanningServiceV3 coursePlanningService;
    @Autowired
    private CourseServiceV3 courseService;

    @PostConstruct
    private void setup() {
        coursePlanningService.planStudentsForCourses();
    }

    @Test
    @Transactional
    void update_student_name_requires_load_entity() {
        Long knownId = studentRepository.findAll().get(0).getId();

        StudentV3 someStudent = studentRepository.getById(knownId);
        someStudent.setName("New name");

        studentRepository.saveAndFlush(someStudent);
        assertThat(studentRepository.getById(knownId).getName()).isEqualTo("New name");
    }

    @Test
    @Transactional
    void use_modifying_query_to_update_name_clear_session_afterwards() {
        Long knownId = studentRepository.findAll().get(0).getId();

        assertThat(studentRepository.updateStudentName(knownId, "New name")).isEqualTo(1);
        assertThat(studentRepository.getById(knownId).getName()).isEqualTo("New name");
    }

    @Test
    @Transactional
    void use_views_to_load_only_necessary_data() {
        CourseV3 course = courseRepository.findAll().get(0);
        StudentV3 student = studentRepository.findAll().get(0);

        System.out.println("Query names");
        List<String> students = studentRepository.findAllStudentNamesAttendingCourse(course.getCourseName());
        System.out.println("Query named entity");
        List<NamedEntity> students2 = studentRepository.findByCourse_CourseName(course.getCourseName());
        System.out.println("Query view");
        List<StudentView> students3 = studentRepository.findStudentsByName(course.getCourseName(), StudentView.class);
        System.out.println("Query view full");
        List<StudentViewFull> students4 = studentRepository.findStudentsByName(course.getCourseName(), StudentViewFull.class);

        assertThat(students.size()).isGreaterThan(0);
        List<String> onlyNames = students2.stream().map(NamedEntity::getName).toList();
        assertThat(students).containsExactly(onlyNames.toArray(String[]::new));
        System.out.println(onlyNames);
        System.out.println(students3);
        System.out.println(students4);
    }

    @Test
    @Transactional
    void order_inserts() {
        for (int i = 0; i < 10; i++) {
            CourseV3 course = new CourseV3("Test course " + i);
            CourseV3 savedCourse = courseRepository.save(course);
            StudentV3 student = new StudentV3("Test student " + i);
            student.setCourse(savedCourse);
            studentRepository.save(student);
        }
        courseRepository.flush();
    }

    @Test
    @Transactional
    void use_query_with_paging() {
        CourseV3 course = courseRepository.findAll().get(0);

        Pageable page = Pageable.ofSize(1).withPage(0);
        List<StudentV3> students = studentRepository.findAllStudentsAttendingCourse(course.getCourseName(), page);
        assertThat(students.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    void countApprox() {
        Pageable page = Pageable.ofSize(2).withPage(0);
        List<StudentV3> students = studentRepository.findAllStudentsAttendingCourse("Course 1", page);
        if (students.isEmpty()) {
            // no students
            System.out.println("No students");
        } else if (students.size() == 1) {
            // unique student
            System.out.println("One student");
        } else {
            // not unique
            System.out.println("Not unique student");
        }
    }

    @Test
    @Transactional
    void fetch_and_pagination_problem() {
        List<CourseV3> courses = courseRepository.loadAllFetchStudents(Pageable.ofSize(2).withPage(0));
        assertThat(courses.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void long_running_transaction() throws InterruptedException {
        Long courseId = courseRepository.findAll().get(0).getId();

        new Thread(() -> courseService.waitForCourseToFinish(courseId)).start();
        new Thread(() -> courseService.waitForCourseToFinish(courseId)).start();
        new Thread(() -> courseService.waitForCourseToFinish(courseId)).start();
        new Thread(() -> courseService.waitForCourseToFinish(courseId)).start();
        new Thread(() -> courseService.waitForCourseToFinish(courseId)).start();
        new Thread(() -> courseService.waitForCourseToFinish(courseId)).start();
        new Thread(() -> courseService.waitForCourseToFinish(courseId)).start();
        new Thread(() -> courseService.waitForCourseToFinish(courseId)).start();
        new Thread(() -> courseService.waitForCourseToFinish(courseId)).start();
        new Thread(() -> courseService.waitForCourseToFinish(courseId)).start();
        Thread thread = new Thread(() -> {
            courseService.waitForCourseToFinish(courseId);
        });

        thread.start();
        thread.join();
    }
}