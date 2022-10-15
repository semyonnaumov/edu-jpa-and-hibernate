package fintech.middle.jpa.v4;

import fintech.middle.jpa.v4.model.StudentV4;
import fintech.middle.jpa.v4.repository.StudentRepositoryV4;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FintechMiddleJpaApplicationTests {
    @Autowired
    private StudentRepositoryV4 studentRepository;

    @Test
    @Transactional
    void lsat_modified_changes() {
        StudentV4 student = new StudentV4();
        student.setName("Name 1");
        var savedStudent = studentRepository.saveAndFlush(student);

        savedStudent.setName("Name 2");
        var updatedStudent = studentRepository.saveAndFlush(student);

        assertThat(updatedStudent.getCreationDate()).isEqualTo(savedStudent.getCreationDate());
        assertThat(updatedStudent.getModifyDate()).isAfter(savedStudent.getCreationDate());
        assertThat(savedStudent.getCreatedBy()).isEqualTo("Current user name");
        assertThat(savedStudent.getModifiedBy()).isEqualTo("Current user name");
    }

    @Test
    void create_history_record() {
        StudentV4 auditedStudent = new StudentV4();
        auditedStudent.setName("Name 1");
        StudentV4 savedStudent = studentRepository.saveAndFlush(auditedStudent);

        savedStudent.setName("Name 2");
        StudentV4 updatedStudent = studentRepository.saveAndFlush(auditedStudent);

        var revisions = studentRepository.findRevisions(auditedStudent.getId());
        revisions.forEach(System.out::println);
    }

}