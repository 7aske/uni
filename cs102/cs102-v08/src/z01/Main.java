package z01;

import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Stack<Integer> stack0 = new Stack<>();
		Stack<Integer> stack1 = new Stack<>();

		stack0.push(1);
		stack0.push(2);
		stack0.push(3);

		for (Integer i : stack0) {
			stack1.push(i);
		}
		while (!stack1.isEmpty()){
			System.out.printf("s0- %d|%d -s1\n", stack0.pop(), stack1.pop());
		}
	}
}
