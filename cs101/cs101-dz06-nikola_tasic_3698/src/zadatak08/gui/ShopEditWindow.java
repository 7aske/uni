package zadatak08.gui;

import zadatak08.gui.dims.Dims;
import zadatak08.shop.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ShopEditWindow extends JFrame {
	static Dims dims = new Dims();
	private Shop shop;
	private ProductWindow productWindow;

	private JPanel panel = new JPanel();
	private JTextField shopEditName = new JTextField();

	private ConfirmButton confirm;
	private AddProductButton addProductButton;
	private RemoveProductButton removeProductButton;

	public ShopEditWindow(MainWindow w){
		this.shop = w.getShop();

		this.confirm = new ConfirmButton(this, w);
		this.addProductButton = new AddProductButton(this, w);
		this.removeProductButton = new RemoveProductButton(this, w);


		this.shopEditName.setPreferredSize(dims.labelDim);
		this.shopEditName.setHorizontalAlignment(SwingConstants.CENTER);
		this.updateContents();

		this.setSize(dims.windowDim);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	void updateContents(){
		this.panel.removeAll();
		this.panel.repaint();
		this.panel.revalidate();
		this.remove(this.panel);
		this.revalidate();
		this.repaint();
		this.panel.add(this.shopEditName);
		ArrayList<Product> products = this.shop.getProducts();
		this.shopEditName.setText(shop.getName());
		for(int i = 0; i < products.size(); i++){
			ProductPanel productPanel = new ProductPanel(products.get(i));
			this.panel.add(productPanel);
		}

		this.panel.add(this.confirm);
		this.panel.add(this.addProductButton);
		this.panel.add(this.removeProductButton);
		this.add(this.panel);

	}
	Shop getShop(){
		return shop;
	}
	String getInputName(){
		return shopEditName.getText();
	}
	void updateShop(String name){
		this.shop.setName(name);
	}
	void openProductWindow(){
		this.productWindow = new ProductWindow(this.shop, this);
	}
}
class ConfirmButton extends JButton {
	static Dims dims = new Dims();

	public ConfirmButton(ShopEditWindow sw, MainWindow w){
		this.setSize(dims.buttonDim);
		this.setText("Confirm");
		this.addActionListener(e->{
			if(!sw.getShop().getName().equals(sw.getInputName())){
				sw.updateShop(sw.getInputName());
			}
			w.updateContents();
			sw.dispatchEvent(new WindowEvent(sw, WindowEvent.WINDOW_CLOSING));
		});
	}
}
class AddProductButton extends JButton {
	static Dims dims = new Dims();

	public AddProductButton(ShopEditWindow sw, MainWindow w){
		this.setText("Add product");
		this.setSize(new Dimension(dims.buttonDim));
		this.addActionListener(e->{
			sw.openProductWindow();
			w.updateContents();
		});
	}
}
class ProductPanel extends JPanel{
	static Dims dims = new Dims();
	private Product product;
	private JLabel nameLabel = new JLabel();
	private JLabel brandLabel = new JLabel();
	private JLabel priceLabel = new JLabel();
	private JLabel barcodeLabel = new JLabel();
	private JLabel stockLabel = new JLabel();
	public ProductPanel(Product p){
		this.product = p;

		this.nameLabel.setSize(dims.labelDim);
		this.brandLabel.setSize(dims.labelDim);
		this.priceLabel.setSize(dims.labelDim);
		this.barcodeLabel.setSize(dims.labelDim);
		this.stockLabel.setSize(dims.labelDim);

		this.nameLabel.setText(String.format("Name: %s", this.product.getName()));
		this.brandLabel.setText(String.format("Brand: %s", this.product.getBrand()));
		this.priceLabel.setText(String.format("Price: %s", this.product.getPrice()));
		this.barcodeLabel.setText(String.format("Barcode: %s", this.product.getBarcode()));
		this.stockLabel.setText(String.format("Stock: %s", this.product.getStock()));

		this.add(this.nameLabel);
		this.add(this.brandLabel);
		this.add(this.priceLabel);
		this.add(this.barcodeLabel);
		this.add(this.stockLabel);
	}
}
class RemoveProductButton extends JButton{
	static Dims dims = new Dims();
	public RemoveProductButton(ShopEditWindow sw, MainWindow w) {
		this.setSize(dims.buttonDim);
		this.setText("Remove product");
		this.addActionListener(e->{
			int index = Integer.parseInt(JOptionPane.showInputDialog(sw, "Enter product index number: ")) -1;
			sw.getShop().removeProduct(index);
			sw.updateContents();
			w.updateContents();
		});

	}
}
