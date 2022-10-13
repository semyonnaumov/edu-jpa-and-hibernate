package fintech.middle.jpa.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "v1Student")
@Table(name = "students1")
@EntityListeners({AuditingEntityListener.class})
public class Student extends NamedEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated
    private Status status;

    @LastModifiedDate
    private LocalDateTime modifyDate;

    @CreatedDate
    private LocalDateTime creationDate;

    public enum Status {
        ONE,
        TWO
    }
}
