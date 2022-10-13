package fintech.middle.jpa.v2.service;

import fintech.middle.jpa.v2.model.Course;
import fintech.middle.jpa.v2.model.Student;
import fintech.middle.jpa.v2.repository.CourseRepository;
import fintech.middle.jpa.v2.repository.StudentRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepositoryV2 studentRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentRepositoryV2 studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long prepareCourse() {
        Course course = new Course("Fintech.Middle JPA");
        Student student = new Student("Ivan");

        course.getStudentsApplied().add(student);

        course.getStudentsApplied().remove(0);

        Student petya = new Student("Petya");
        petya.setCourse(course);
        course.addStudent(petya);

        Student kolya = new Student("Kolya");
        kolya.setCourse(course);
        course.addStudent(kolya);

        Course savedCourse = courseRepository.save(course);
        return savedCourse.getId();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long changeCourseName() {
        Long courseId = prepareCourse();
        Course savedCourse = courseRepository.getById(courseId);
        savedCourse.setCourseName("Math");
        courseRepository.save(savedCourse);

        savedCourse.setCourseName("Mathematix");
        courseRepository.save(savedCourse);

        savedCourse.setCourseName("Mathematics");

        return courseId;
    }
}
