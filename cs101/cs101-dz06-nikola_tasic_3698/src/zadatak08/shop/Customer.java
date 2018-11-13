package zadatak08.shop;

import java.util.UUID;

public class Customer {
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String phone;
	private boolean member;
	private String uid;

	public Customer(String firstName, String lastName, String address, String email, String phone, boolean member) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.member = member;
		this.uid = UUID.randomUUID().toString().split("-")[0];
	}

	public Customer(String firstName, String lastName, String address, String email, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.member = false;
		this.uid = UUID.randomUUID().toString().split("-")[0];
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isMember() {
		return member;
	}

	public void setMember(boolean member) {
		this.member = member;
	}

	public String getUid() {
		return uid;
	}
}
