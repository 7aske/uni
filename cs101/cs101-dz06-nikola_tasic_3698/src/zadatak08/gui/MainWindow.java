package zadatak08.gui;

import zadatak08.shop.*;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
	private Shop shop = new Shop("Example Shop");


	private JPanel panel = new JPanel();
	private ShopWindow shopWindow;

	private JLabel shopNameLabel = new JLabel();

	private JLabel shopProductCountLabel = new JLabel();
	private JLabel shopPurchaseCountLabel = new JLabel();
	private JLabel shopRevenueLabel = new JLabel();

	private ShopEditButton shopEditButton = new ShopEditButton(this);

	public MainWindow() {
		Dimension labelDim = new Dimension(400,30);
		Dimension buttonDim = new Dimension(50,20);
		Dimension windowDim = new Dimension(450, 500);

		this.panel.setSize(windowDim);

		this.updateContents();

		this.shopNameLabel.setPreferredSize(labelDim);
		this.shopPurchaseCountLabel.setPreferredSize(labelDim);
		this.shopProductCountLabel.setPreferredSize(labelDim);
		this.shopRevenueLabel.setPreferredSize(labelDim);
		this.shopEditButton.setSize(buttonDim);

		this.shopNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.shopEditButton.setVerticalAlignment(SwingConstants.BOTTOM);

		this.shopEditButton.setLocation(300,300);


		this.panel.add(this.shopNameLabel);
		this.panel.add(this.shopProductCountLabel);
		this.panel.add(this.shopPurchaseCountLabel);
		this.panel.add(this.shopRevenueLabel);
		this.panel.add(this.shopEditButton);
		this.add(panel);

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Shop Manager");
		this.setSize(windowDim);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public Shop getShop() {
		return shop;
	}

	public void openShopWindow(){
		this.shopWindow = new ShopWindow(this);
	}
	public void updateContents(){
		this.shopNameLabel.setText(this.shop.getName());
		this.shopProductCountLabel.setText(String.format("Number of products:      %d", this.shop.getProducts().size()));
		this.shopPurchaseCountLabel.setText(String.format("Number of purchases:   %d", this.shop.getPurchases().size()));
		this.shopRevenueLabel.setText(String.format("Total revenue:                  %.2f", this.shop.getRevenue()));
	}
}
class ShopEditButton extends JButton {
	public ShopEditButton(MainWindow w){
		this.setText("Edit");

		this.addActionListener(e->{
			w.openShopWindow();
		});
	}
}