package task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager extends Employee {
	private List<Employee> employees = new ArrayList<>();
	private Map<String, List<Employee>> teams = new HashMap<>();

	public Manager() {
	}

	public Manager(String firstName, String lastName, String jmbg, double wage) {
		super(firstName, lastName, jmbg, wage);
	}
}
