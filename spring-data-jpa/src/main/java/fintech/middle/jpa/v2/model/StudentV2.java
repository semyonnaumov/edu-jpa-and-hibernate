package fintech.middle.jpa.v2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "v2Students")
@Table(name = "students2")
public class StudentV2 {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    private CourseV2 course;

    public StudentV2(String name) {
        this.name = name;
    }
}