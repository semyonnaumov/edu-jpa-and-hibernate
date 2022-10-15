package fintech.middle.jpa.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "v1Students")
@Table(name = "students1")
public class StudentV1 extends NamedEntity {
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
        ACTIVE,
        EXPELLED
    }
}
