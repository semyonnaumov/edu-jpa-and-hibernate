package fintech.middle.jpa.v3.service;

import fintech.middle.jpa.v3.model.CourseV3;
import fintech.middle.jpa.v3.model.StudentV3;
import fintech.middle.jpa.v3.repository.CourseRepositoryV3;
import fintech.middle.jpa.v3.repository.StudentRepositoryV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CoursePlanningServiceV3 {
    private final CourseRepositoryV3 courseRepository;
    private final StudentRepositoryV3 studentRepository;

    @Autowired
    public CoursePlanningServiceV3(CourseRepositoryV3 courseRepository, StudentRepositoryV3 studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void planStudentsForCourses() {
        CourseV3 course1 = new CourseV3("Course 1");
        StudentV3 student11 = new StudentV3("Student 1.1");
        student11.setCourse(course1);
        course1.addStudent(student11);
        StudentV3 student12 = new StudentV3("Student 1.2");
        student12.setCourse(course1);
        course1.addStudent(student12);

        CourseV3 course2 = new CourseV3("Course 2");
        StudentV3 student21 = new StudentV3("Student 2.1");
        student21.setCourse(course2);
        course2.addStudent(student21);
        StudentV3 student22 = new StudentV3("Student 2.2");
        student22.setCourse(course2);
        course2.addStudent(student22);

        CourseV3 course3 = new CourseV3("Course 3");
        StudentV3 student31 = new StudentV3("Student 3.1");
        student31.setCourse(course3);
        course3.addStudent(student31);
        StudentV3 student32 = new StudentV3("Student 3.2");
        student32.setCourse(course3);
        course3.addStudent(student32);

        studentRepository.save(student11);
        studentRepository.save(student12);
        studentRepository.save(student21);
        studentRepository.save(student22);
        studentRepository.save(student31);
        studentRepository.save(student32);

        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
    }
}
