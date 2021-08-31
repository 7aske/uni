package service.impl;

import org.springframework.stereotype.Component;
import service.Animal;

public class Sheep implements Animal {
	@Override
	public void makeSound() {
		System.out.println("Blah blaah");
	}

	@Override
	public void feedAnimal() {
		System.out.println("Feeding sheep");
	}
}
