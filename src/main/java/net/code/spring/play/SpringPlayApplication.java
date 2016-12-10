package net.code.spring.play;

import com.github.javafaker.Faker;
import net.code.spring.play.person.Person;
import net.code.spring.play.person.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringPlayApplication {

	private final Faker faker = new Faker();

	public static void main(String[] args) {
		SpringApplication.run(SpringPlayApplication.class, args);
	}

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

}
