package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application-Klasse mit der main()-Methode um die Applikation zu starten.
 * 
 * @author Michael Nickel
 */
@SpringBootApplication
@ComponentScan({"rest","app"})
@EntityScan("entity")
@EnableJpaRepositories("repository")
@EnableJpaAuditing
public class FragebogenIfbApplication {

	public static void main(String[] args) {
		SpringApplication.run(FragebogenIfbApplication.class, args);
	}
}
