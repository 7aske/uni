package zadatak08.gui;

import zadatak08.gui.dims.Dims;
import zadatak08.shop.Customer;
import zadatak08.shop.Shop;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class CustomerWindow extends JFrame {
	static Dims dims = new Dims();

	private JPanel panel = new JPanel();
	private Shop shop;

	private MainWindow mainWindow;
	private CustomerEditWindow customerEditWindow;

	private CloseButton closeButton = new CloseButton(this);
	private AddCustomerButton addCustomerButton = new AddCustomerButton(this);

	public CustomerWindow(MainWindow w) {
		this.shop = w.getShop();
		this.mainWindow = w;
		this.updateContents();

		this.setSize(dims.windowDim);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	void updateContents() {
		this.panel.removeAll();
		this.panel.repaint();
		this.panel.revalidate();
		this.remove(this.panel);
		this.repaint();
		this.revalidate();
		ArrayList<Customer> customers = this.mainWindow.getShop().getCustomers();
		for (int i = 0; i < ((ArrayList) customers).size(); i++) {
			CustomerPanel cpanel = new CustomerPanel(customers.get(i));
			this.panel.add(cpanel);
		}

		this.panel.add(this.addCustomerButton);
		this.panel.add(this.closeButton);
		this.add(panel);

	}

	public void openCustomerEditWindow() {
		this.customerEditWindow = new CustomerEditWindow(this, this.shop);
	}
}

class CustomerPanel extends JPanel {
	static Dims dims = new Dims();


	private JLabel nameLabel = new JLabel();
	private JLabel addressLabel = new JLabel();
	private JLabel emailLabel = new JLabel();
	private JLabel phoneLabel = new JLabel();
	private JLabel statusLabel = new JLabel();
	private JLabel idLabel = new JLabel();

	public CustomerPanel(Customer c) {
		this.nameLabel.setSize(dims.labelDim);
		this.addressLabel.setSize(dims.labelDim);
		this.emailLabel.setSize(dims.labelDim);
		this.phoneLabel.setSize(dims.labelDim);
		this.statusLabel.setSize(dims.labelDim);
		this.idLabel.setSize(dims.labelDim);

		this.nameLabel.setText(String.format("Name: %s %s", c.getFirstName(), c.getLastName()));
		this.addressLabel.setText("Addr: " + c.getAddress());
		this.emailLabel.setText("E-mail: " + c.getEmail());
		this.phoneLabel.setText("Phone: " + c.getPhone());
		this.statusLabel.setText("Member: " + Boolean.toString(c.isMember()));
		this.idLabel.setText("ID: " + c.getUid());

		this.add(this.nameLabel);
		this.add(this.addressLabel);
		this.add(this.phoneLabel);
		this.add(this.statusLabel);
		this.add(this.idLabel);

	}
}

class CloseButton extends JButton {
	static Dims dims = new Dims();

	public CloseButton(CustomerWindow cw) {
		this.setSize(dims.buttonDim);
		this.setText("Close");
		this.addActionListener(e -> {
			cw.dispatchEvent(new WindowEvent(cw, WindowEvent.WINDOW_CLOSING));
		});
	}
}

class AddCustomerButton extends JButton {
	static Dims dims = new Dims();

	public AddCustomerButton(CustomerWindow cw) {
		this.setSize(dims.buttonDim);
		this.setText("Add customer");
		this.addActionListener(e -> {
			cw.openCustomerEditWindow();
		});
	}
}