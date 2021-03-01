package service.impl;

import org.springframework.stereotype.Component;
import service.Animal;

public class Dog implements Animal {
	@Override
	public void makeSound() {
		System.out.println("Bark bark");
	}

	@Override
	public void feedAnimal() {
		System.out.println("Feeding dog");
	}
}
