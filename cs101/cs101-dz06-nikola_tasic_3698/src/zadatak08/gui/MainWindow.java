package zadatak08.gui;

import zadatak08.shop.Shop;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

	private JTextField shopNameLabel = new JTextField("");

	private JTextField shopNameField = new JTextField("");
	private JTextField shopProductCountLabel = new JTextField("");
	private JTextField shopPurchaseCountLabel = new JTextField("");
	private JLabel shopRevenueLabel = new JLabel("");

	private ShopWindow shopWindow = new ShopWindow();

	private Shop shop = new Shop("Example Shop");


	public MainWindow() {
		this.shopNameLabel.setText(this.shop.getName());
		this.shopProductCountLabel.setText(String.format("Number of products: %d", this.shop.getProducts().size()));
		this.shopPurchaseCountLabel.setText(String.format("Number of purchases: %d", this.shop.getPurchases().size()));

		this.shopNameLabel.setPreferredSize(new Dimension(500,30));
		this.shopPurchaseCountLabel.setPreferredSize(new Dimension(500,30));
		this.shopProductCountLabel.setPreferredSize(new Dimension(500,30));

		this.add(this.shopNameLabel);
		this.add(this.shopProductCountLabel);
		this.add(this.shopPurchaseCountLabel);
		this.pack();

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Shop Manager");
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}