package fintech.middle.jpa.v1;

import fintech.middle.jpa.v1.model.Student;
import fintech.middle.jpa.v1.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FintechMiddleJpaApplicationTests {
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void cleanup_tables() {
        studentRepository.deleteAll();
    }

    @Test
    void create_then_load_new_student() {
        Student s = new Student();
        s.setName("Ivan");
        Student savedStudent = studentRepository.save(s);

        assertThat(savedStudent.getId()).isGreaterThan(0L);

        assertThat(studentRepository.getById(savedStudent.getId())).isNotNull();
        assertThat(studentRepository.findById(savedStudent.getId())).isPresent();

        studentRepository.save(new Student());
        assertThat(studentRepository.findAll().size()).isEqualTo(2);

        studentRepository.deleteById(savedStudent.getId());
        assertThat(studentRepository.findById(savedStudent.getId())).isNotPresent();
    }

    @Test
    void find_student_by_name() {
        Student s = new Student();
        s.setName("Ivan");
        Student savedStudent = studentRepository.save(s);

        Student foundStudent = studentRepository.findByName("Ivan");

        assertThat(savedStudent.getId()).isEqualTo(foundStudent.getId());
    }
}