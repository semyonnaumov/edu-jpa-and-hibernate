package fintech.middle.jpa.v3.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "v3Courses")
@Table(name = "courses3")
@Cacheable
public class CourseV3 {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courses3_seq"
    )
    @SequenceGenerator(
            name = "courses3_seq",
            sequenceName = "courses3_seq",
            allocationSize = 10
    )
    private Long id;
    private String courseName;
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<StudentV3> studentsApplied = new ArrayList<>();
    private LocalDate endDate = null;

    public CourseV3(String courseName) {
        this();
        this.courseName = courseName;
    }

    public CourseV3() {
    }

    public void addStudent(StudentV3 student) {
        this.studentsApplied.add(student);
        student.setCourse(this);
    }

    public void removeStudent(StudentV3 student) {
        this.studentsApplied.remove(student);
        student.setCourse(null);
    }
}
