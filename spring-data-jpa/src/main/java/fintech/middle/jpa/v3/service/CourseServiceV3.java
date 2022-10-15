package fintech.middle.jpa.v3.service;

import fintech.middle.jpa.v3.model.CourseV3;
import fintech.middle.jpa.v3.repository.CourseRepositoryV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceV3 {
    private final CourseRepositoryV3 courseRepository;

    @Autowired
    public CourseServiceV3(CourseRepositoryV3 courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CourseV3 waitForCourseToFinish(Long courseId) {
        CourseV3 course = courseRepository.getById(courseId);
        // imitate calling another web-service
        while (course.getEndDate() == null) {
            try {
                Thread.sleep(5000);
                System.out.println("Still waiting for course to finish in thread " + Thread.currentThread().getName());
                return course;
            } catch (InterruptedException ignored) {}
        }
        return course;
    }
}
