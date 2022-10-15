package fintech.middle.jpa.v4.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "v4Students")
@Table(name = "students4")
@EntityListeners(AuditingEntityListener.class)
@Audited
public class StudentV4 {
    @Id
    @GeneratedValue(generator = "students4_seq")
    @SequenceGenerator(name = "students4_seq")
    private Long id;
    private String name;
    private String familyName;

    // audit:
    @LastModifiedDate
    private LocalDateTime modifyDate;
    @CreatedDate
    private LocalDateTime creationDate;
    @CreatedBy
    String createdBy;
    @LastModifiedBy
    String modifiedBy;
}
