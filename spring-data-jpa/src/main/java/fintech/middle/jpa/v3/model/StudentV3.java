package fintech.middle.jpa.v3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "v3Students")
@Table(name = "students3")
public class StudentV3 {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "students3_seq"
    )
    @SequenceGenerator(
            name = "students3_seq",
            sequenceName = "students3_seq",
            allocationSize = 10
    )
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private CourseV3 course;

    public StudentV3(String name) {
        this.name = name;
    }
}