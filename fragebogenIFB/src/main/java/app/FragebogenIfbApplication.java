package app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.access.SecurityConfig;


@ComponentScan("rest")
@EntityScan("entity")
@EnableJpaRepositories("repository")
@EnableJpaAuditing
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class FragebogenIfbApplication {

	public static void main(String[] args) {
		SpringApplication.run(FragebogenIfbApplication.class, args);
	}
}
