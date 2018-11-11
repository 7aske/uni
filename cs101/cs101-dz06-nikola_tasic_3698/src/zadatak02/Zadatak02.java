package zadatak02;

import java.util.Scanner;

public class Zadatak02 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.printf("How many orders do you want: ");
		Order[] orders = new Order[input.nextInt()];
		for (int i = 0; i < orders.length; i++) {
			System.out.printf("\nOrder no. %d\n", i+1);
			System.out.printf("How many windows in this order: ");
			Window[] windows = new Window[input.nextInt()];
			for (int j = 0; j < windows.length; j++) {
				System.out.printf("\nWindow no. %d\n", j+1);
				System.out.printf("Enter window height:");
				double height = input.nextDouble();
				System.out.printf("Enter window width:");
				double width = input.nextDouble();
				System.out.printf("Enter window depth:");
				double depth = input.nextDouble();
				System.out.printf("Enter window price:");
				double price = input.nextDouble();
				windows[j] = new Window(height, width, depth, price);
			}
			System.out.printf("Enter the address: ");
			String address = input.nextLine();
			orders[i] = new Order(windows, address);
		}
		input.close();
		for (int k = 0; k < orders.length; k++) {
			System.out.printf("\nOrder ID: %d\nOrder address: %s\nNumber of windows: %d\nOrder price: %.2f", orders[k].getId(), orders[k].getAddress(), orders[k].getWindows().length, orders[k].getPrice());
		}
	}
}
