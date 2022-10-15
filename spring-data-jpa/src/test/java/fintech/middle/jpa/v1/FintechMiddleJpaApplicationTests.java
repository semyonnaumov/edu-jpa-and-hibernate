package fintech.middle.jpa.v1;

import fintech.middle.jpa.v1.model.StudentV1;
import fintech.middle.jpa.v1.repository.StudentRepositoryV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FintechMiddleJpaApplicationTests {
    @Autowired
    private StudentRepositoryV1 studentRepository;

    @BeforeEach
    void cleanup_tables() {
        studentRepository.deleteAll();
    }

    @Test
    void create_then_load_new_student() {
        StudentV1 s = new StudentV1();
        s.setName("Ivan");
        StudentV1 savedStudent = studentRepository.save(s);

        assertThat(savedStudent.getId()).isGreaterThan(0L);

        assertThat(studentRepository.getById(savedStudent.getId())).isNotNull();
        assertThat(studentRepository.findById(savedStudent.getId())).isPresent();

        studentRepository.save(new StudentV1());
        assertThat(studentRepository.findAll().size()).isEqualTo(2);

        studentRepository.deleteById(savedStudent.getId());
        assertThat(studentRepository.findById(savedStudent.getId())).isNotPresent();
    }

    @Test
    void find_student_by_name() {
        StudentV1 s = new StudentV1();
        s.setName("Ivan");
        StudentV1 savedStudent = studentRepository.save(s);

        StudentV1 foundStudent = studentRepository.findByName("Ivan");

        assertThat(savedStudent.getId()).isEqualTo(foundStudent.getId());
    }
}