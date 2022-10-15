package fintech.middle.jpa.v2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "v2Courses")
public class CourseV2 {
    @Id
    @GeneratedValue
    private Long id;
    private String courseName;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentV2> studentsApplied = new ArrayList<>();
    private LocalDate endDate = null;

    public CourseV2(String courseName) {
        this();
        this.courseName = courseName;
    }

    public CourseV2() {
    }

    public void addStudent(StudentV2 student) {
        this.studentsApplied.add(student);
        student.setCourse(this);
    }

    public void removeStudent(StudentV2 student) {
        this.studentsApplied.remove(student);
        student.setCourse(null);
    }
}
