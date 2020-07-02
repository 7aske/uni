package rs.ac.metropolitan.cs230.model;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.util.List;

@Named(value = "student")
@RequestScoped
public class Student {

	private String name;
	private Integer index;
	private String status;
	private boolean traditional;
	private String street;
	private String city;
	private Integer zipcode;
	private List<String> phoneNumbers;
	private String role;

	public Student(String name, Integer index, String status, boolean traditional, String street, String city, Integer zipcode, List<String> phoneNumbers, String role) {
		this.name = name;
		this.index = index;
		this.status = status;
		this.traditional = traditional;
		this.street = street;
		this.city = city;
		this.zipcode = zipcode;
		this.phoneNumbers = phoneNumbers;
		this.role = role;
	}

	public Student() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isTraditional() {
		return traditional;
	}

	public void setTraditional(boolean traditional) {
		this.traditional = traditional;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
