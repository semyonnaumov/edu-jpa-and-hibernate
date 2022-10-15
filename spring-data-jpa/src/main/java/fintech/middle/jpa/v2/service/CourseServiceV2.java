package fintech.middle.jpa.v2.service;

import fintech.middle.jpa.v2.model.CourseV2;
import fintech.middle.jpa.v2.model.StudentV2;
import fintech.middle.jpa.v2.repository.CourseRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CourseServiceV2 {
    private final CourseRepositoryV2 courseRepository;

    @Autowired
    public CourseServiceV2(CourseRepositoryV2 courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long prepareCourse() {
        CourseV2 course = new CourseV2("Fintech.Middle JPA");
        StudentV2 student = new StudentV2("Ivan");

        course.getStudentsApplied().add(student);

        course.getStudentsApplied().remove(0);

        StudentV2 petya = new StudentV2("Petya");
        petya.setCourse(course);
        course.addStudent(petya);

        StudentV2 kolya = new StudentV2("Kolya");
        kolya.setCourse(course);
        course.addStudent(kolya);

        CourseV2 savedCourse = courseRepository.save(course);
        return savedCourse.getId();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long changeCourseName() {
        Long courseId = prepareCourse();
        CourseV2 savedCourse = courseRepository.getById(courseId);
        savedCourse.setCourseName("Math");
        courseRepository.save(savedCourse);

        savedCourse.setCourseName("Mathematix");
        courseRepository.save(savedCourse);

        savedCourse.setCourseName("Mathematics");

        return courseId;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void prepareCourses() {
        CourseV2 course1 = new CourseV2("Course 1");
        StudentV2 student11 = new StudentV2("Student 1.1");
        student11.setCourse(course1);
        course1.addStudent(student11);
        StudentV2 student12 = new StudentV2("Student 1.2");
        student12.setCourse(course1);
        course1.addStudent(student12);

        CourseV2 course2 = new CourseV2("Course 2");
        StudentV2 student21 = new StudentV2("Student 2.1");
        student21.setCourse(course2);
        course2.addStudent(student21);
        StudentV2 student22 = new StudentV2("Student 2.2");
        student22.setCourse(course2);
        course2.addStudent(student22);

        CourseV2 course3 = new CourseV2("Course 3");
        StudentV2 student31 = new StudentV2("Student 3.1");
        student31.setCourse(course3);
        course3.addStudent(student31);
        StudentV2 student32 = new StudentV2("Student 3.2");
        student32.setCourse(course3);
        course3.addStudent(student32);

        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
    }
}
