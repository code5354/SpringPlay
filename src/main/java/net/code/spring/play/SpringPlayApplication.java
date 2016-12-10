package net.code.spring.play;

import com.github.javafaker.Faker;
import net.code.spring.play.person.Person;
import net.code.spring.play.person.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringPlayApplication {

	private final Faker faker = new Faker();

	public static void main(String[] args) {
		SpringApplication.run(SpringPlayApplication.class, args);
	}


	// Generate fake data at startup
	@Bean
	public CommandLineRunner initializeDb(PersonRepository repository){
		return (args) -> {
			repository.deleteAll();
			//Insert some random pies
			for(int i = 0; i < 20; i++) {
				repository.save(new Person(faker.lorem().word(), faker.lorem().word()));
			}
		};
	}


	// Enable Swagger
	@Bean
	public Docket swaggerSettings() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/");
	}
}
