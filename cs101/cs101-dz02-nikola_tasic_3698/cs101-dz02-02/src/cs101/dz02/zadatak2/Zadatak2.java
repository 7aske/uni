/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs101.dz02.zadatak2;

import java.util.Scanner;

/**
 *
 * @author nik
 */
public class Zadatak2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	new Zadatak2();
    }

    public Zadatak2() {
	/* 
	    README - preferiram da sve pisem na engleskom
	    nadam se da to nije problem	   
	 */
	final float startTime = System.nanoTime();
	Scanner input = new Scanner(System.in);
	float a, b, c, r;

	System.out.print("Enter number a: ");
	while (!input.hasNextFloat()) {
	    System.out.print("It has to be a number!\n");
	    System.out.print("Enter number a: ");
	    input.nextLine();
	}
	a = input.nextFloat();

	System.out.print("Enter number b: ");
	while (!input.hasNextFloat()) {
	    System.out.print("It has to be a number!\n");
	    System.out.print("Enter number b: ");
	    input.nextLine();
	}

	b = input.nextFloat();

	System.out.print("Enter number c: ");
	while (!input.hasNextFloat()) {
	    System.out.print("It has to be a number!\n");
	    System.out.print("Enter number c: ");
	    input.nextLine();
	}
	c = input.nextFloat();

	r = (a + b + c) / 3;

	final float endTime = System.nanoTime();
	final float execTime = (endTime - startTime) / 1000000;	 // Execution time in miliseconds

	System.out.printf("Average of numbers %.2f %.2f %.2f is %.2f\n"
		+ "Execution time of the program was %.2f milliseconds\n", a, b, c, r, execTime);
    }

}
