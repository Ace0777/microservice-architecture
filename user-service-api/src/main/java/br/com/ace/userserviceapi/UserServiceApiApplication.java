package br.com.ace.userserviceapi;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;


@SpringBootApplication
public class UserServiceApiApplication  {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApiApplication.class, args);
	}

}
