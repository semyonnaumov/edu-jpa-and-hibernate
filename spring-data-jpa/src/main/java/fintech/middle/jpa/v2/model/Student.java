package fintech.middle.jpa.v2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "v2Student")
@Table(name = "students2")
@EntityListeners({AuditingEntityListener.class})
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Enumerated
    private Status status;
    @LastModifiedDate
    private LocalDateTime modifyDate;
    @CreatedDate
    private LocalDateTime creationDate;
    @ManyToOne
    private Course course;

    public Student(String name) {
        this.name = name;
    }

    public enum Status {
        ONE,
        TWO
    }
}