package zadatak08.gui;

import zadatak08.gui.dims.Dims;
import zadatak08.shop.Customer;
import zadatak08.shop.Shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class CustomerEditWindow extends JFrame {
	static Dims dims = new Dims();

	private Shop shop;

	private JPanel panel = new JPanel();

	private JTextField firstNameField = new JTextField();
	private JTextField lastNameField = new JTextField();
	private JTextField addressField = new JTextField();
	private JTextField emailField = new JTextField();
	private JTextField phoneField = new JTextField();
	private JTextField memberField = new JTextField();

	private ConfirmCustomerButton confirmCustomerButton;
	private CloseCustomerButton closeCustomerButton;

	public CustomerEditWindow(CustomerWindow cw, Shop s) {
		this.setTitle("Add new customer");

		this.shop = s;
		this.confirmCustomerButton = new ConfirmCustomerButton(cw, this);
		this.closeCustomerButton = new CloseCustomerButton(this);

		this.firstNameField.setPreferredSize(dims.labelDim);
		this.lastNameField.setPreferredSize(dims.labelDim);
		this.addressField.setPreferredSize(dims.labelDim);
		this.emailField.setPreferredSize(dims.labelDim);
		this.phoneField.setPreferredSize(dims.labelDim);
		this.memberField.setPreferredSize(dims.labelDim);

		this.firstNameField.setText("First Name");
		this.lastNameField.setText("Last Name");
		this.addressField.setText("Address");
		this.emailField.setText("E-mail");
		this.phoneField.setText("Phone");
		this.memberField.setText("Member?");

		this.panel.add(this.firstNameField);
		this.panel.add(this.lastNameField);
		this.panel.add(this.addressField);
		this.panel.add(this.emailField);
		this.panel.add(this.phoneField);
		this.panel.add(this.memberField);


		this.panel.add(this.confirmCustomerButton);
		this.panel.add(this.closeCustomerButton);

		this.add(this.panel);

		this.setSize(dims.windowDim);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public Shop getShop() {
		return this.shop;
	}

	public JTextField getFirstNameField() {
		return firstNameField;
	}

	public JTextField getLastNameField() {
		return lastNameField;
	}

	public JTextField getAddressField() {
		return addressField;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public JTextField getPhoneField() {
		return phoneField;
	}

	public JTextField getMemberField() {
		return memberField;
	}
}

class ConfirmCustomerButton extends JButton {
	static Dims dims = new Dims();

	public ConfirmCustomerButton(CustomerWindow cw, CustomerEditWindow cew) {
		this.setSize(dims.buttonDim);
		this.setText("Confirm");
		this.addActionListener(e -> {
			Customer c = new Customer(
					cew.getFirstNameField().getText(),
					cew.getLastNameField().getText(),
					cew.getAddressField().getText(),
					cew.getEmailField().getText(),
					cew.getPhoneField().getText(),
					Boolean.parseBoolean(cew.getMemberField().getText())
			);
			cew.getShop().addCustomer(c);
			cw.updateContents();
			cew.dispatchEvent(new WindowEvent(cew, WindowEvent.WINDOW_CLOSING));
		});
	}
}
class CloseCustomerButton extends JButton{
	static Dims dims = new Dims();
	public CloseCustomerButton(CustomerEditWindow cew){
		this.setSize(dims.buttonDim);
		this.setText("Closes");
		this.addActionListener(e -> {
			cew.dispatchEvent(new WindowEvent(cew, WindowEvent.WINDOW_CLOSING));
		});
	}
}