package fintech.middle.jpa.v2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String courseName;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> studentsApplied = new ArrayList<>();
    private LocalDate endDate = null;

    public Course(String courseName) {
        this();
        this.courseName = courseName;
    }

    public Course() {
    }

    public void addStudent(Student student) {
        this.studentsApplied.add(student);
        student.setCourse(this);
    }

    public void removeStudent(Student student) {
        this.studentsApplied.remove(student);
        student.setCourse(null);
    }
}
