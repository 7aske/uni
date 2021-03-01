import config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.impl.Chicken;
import service.impl.Dog;
import service.impl.Sheep;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		Chicken chicken = context.getBean(Chicken.class);
		Dog dog = context.getBean(Dog.class);
		Sheep sheep = context.getBean(Sheep.class);
		chicken.feedAnimal();
		chicken.makeSound();
		dog.feedAnimal();
		dog.makeSound();
		sheep.feedAnimal();
		sheep.makeSound();
	}
}
