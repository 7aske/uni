package service.impl;

import org.springframework.stereotype.Component;
import service.Animal;

public class Chicken implements Animal {
	@Override
	public void makeSound() {
		System.out.println("Pew pew");
	}

	@Override
	public void feedAnimal() {
		System.out.println("Feeding chicken");
	}
}
