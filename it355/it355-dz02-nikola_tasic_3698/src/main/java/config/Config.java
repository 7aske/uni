package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.impl.Chicken;
import service.impl.Dog;
import service.impl.Sheep;

@Configuration
public class Config {
	@Bean(name = "dog")
	public Dog dog(){
		return new Dog();
	}
	@Bean(name = "sheep")
	public Sheep sheep(){
		return new Sheep();
	}
	@Bean(name = "chicken")
	public Chicken chicken(){
		return new Chicken();
	}
}
