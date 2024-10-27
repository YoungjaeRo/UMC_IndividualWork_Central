package umc.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StyudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(StyudyApplication.class, args);
	}

}
