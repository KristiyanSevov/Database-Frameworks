package softuni.gamestore;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import softuni.gamestore.config.ModelMapperConfig;

@SpringBootApplication
public class GamestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamestoreApplication.class, args);
	}

	@Bean
	public ModelMapper getMapper(){
		ModelMapper mapper = new ModelMapper();
		ModelMapperConfig config = new ModelMapperConfig(mapper);
		return mapper;
	}
}
