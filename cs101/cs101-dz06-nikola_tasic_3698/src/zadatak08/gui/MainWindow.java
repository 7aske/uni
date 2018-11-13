package zadatak08.gui;

import sun.applet.Main;
import zadatak08.gui.dims.Dims;
import zadatak08.shop.*;

import javax.swing.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {
	static Dims dims = new Dims();

	private Shop shop = new Shop("Example Shop");

	private JPanel panel = new JPanel();
	private ShopEditWindow shopEditWindow;
	private CustomerWindow customerWindow;


	private JLabel shopNameLabel = new JLabel();

	private JLabel shopProductCountLabel = new JLabel();
	private JLabel shopPurchaseCountLabel = new JLabel();
	private JLabel shopRevenueLabel = new JLabel();

	private ShopEditButton shopEditButton = new ShopEditButton(this);
	private CustomerButton customerButton = new CustomerButton(this);
	private BuyButton buyButton = new BuyButton(this);

	public MainWindow() {
		this.panel.setSize(dims.windowDim);

		this.updateContents();

		this.shopNameLabel.setPreferredSize(dims.labelDim);
		this.shopPurchaseCountLabel.setPreferredSize(dims.labelDim);
		this.shopProductCountLabel.setPreferredSize(dims.labelDim);
		this.shopRevenueLabel.setPreferredSize(dims.labelDim);
		this.shopEditButton.setSize(dims.buttonDim);
		this.customerButton.setSize(dims.buttonDim);

		this.shopNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.shopEditButton.setVerticalAlignment(SwingConstants.BOTTOM);

		this.panel.add(this.shopNameLabel);
		this.panel.add(this.shopProductCountLabel);
		this.panel.add(this.shopPurchaseCountLabel);
		this.panel.add(this.shopRevenueLabel);
		this.panel.add(this.shopEditButton);
		this.panel.add(this.customerButton);
		this.panel.add(this.buyButton);
		this.add(panel);

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Shop Manager");
		this.setSize(dims.windowDim);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public Shop getShop() {
		return shop;
	}
	void buy(){
		int prodID = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter product ID:"));
		String uid = JOptionPane.showInputDialog(null, "Enter customer ID:");
		Product product = this.shop.getProducts().get(prodID);
		ArrayList<Product> list = new ArrayList<Product>();
		list.add(product);
		Purchase p = new Purchase(
				list,
				this.shop.getCustomerById(uid)
		);
		this.shop.addPurchase(p);
		this.updateContents();

	}

	public void openShopEditWindow() {
		this.shopEditWindow = new ShopEditWindow(this);
	}
	public void openCustomerWindow(){
		this.customerWindow = new CustomerWindow(this);
	}

	public void updateContents() {
		this.shopNameLabel.setText(this.shop.getName());
		this.shopProductCountLabel.setText(String.format("Number of products:      %d", this.shop.getProducts().size()));
		this.shopPurchaseCountLabel.setText(String.format("Number of purchases:   %d", this.shop.getPurchases().size()));
		this.shopRevenueLabel.setText(String.format("Total revenue:                  %.2f", this.shop.getRevenue()));
	}
}

class ShopEditButton extends JButton {
	public ShopEditButton(MainWindow w) {
		this.setText("Edit Shop");
		this.addActionListener(e -> {
			w.openShopEditWindow();
		});
	}
}
class CustomerButton extends JButton{
	static Dims dims = new Dims();
	public CustomerButton(MainWindow w) {
		this.setText("Customers");
		this.setSize(dims.buttonDim);
		this.addActionListener(e->{
			w.openCustomerWindow();
		});

	}
}
class BuyButton extends JButton {
	static Dims dims = new Dims();

	public BuyButton(MainWindow w) {
		this.setSize(dims.buttonDim);
		this.setText("Buy");
		this.addActionListener(e->{
			w.buy();
		});
	}

}