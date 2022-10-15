package fintech.middle.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class FintechMiddleJpaApplication {
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("Current user name");
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class);
    }
}
