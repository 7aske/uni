package zadatak08.gui;

import zadatak08.shop.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class ShopWindow extends JFrame {
	private Shop shop;
	private ProductWindow productWindow;

	private JPanel panel = new JPanel();
	private JTextField shopEditName = new JTextField();

	private ConfirmButton confirm;
	private AddProductButton addProduct;

	public ShopWindow(MainWindow w){
		Dimension inputDim = new Dimension(250, 20);
		this.shop = w.getShop();

		this.shopEditName.setPreferredSize(inputDim);

		this.shopEditName.setText(shop.getName());

		this.confirm = new ConfirmButton(this, w);
		this.addProduct = new AddProductButton(this, w);


		this.panel.add(this.shopEditName);
		this.panel.add(this.confirm);
		this.panel.add(this.addProduct);
		this.add(panel);
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
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
		this.productWindow = new ProductWindow(this.shop);
	}
}
class ConfirmButton extends JButton {
	public ConfirmButton(ShopWindow sw, MainWindow w){
		Dimension buttonDim = new Dimension(50, 20);
		this.setSize(buttonDim);
		this.setText("Confirm");
		this.addActionListener(e->{
			if(!sw.getShop().getName().equals(sw.getInputName())){
				sw.updateShop(sw.getInputName());
				w.updateContents();
			}
			sw.dispatchEvent(new WindowEvent(sw, WindowEvent.WINDOW_CLOSING));
		});
	}
}
class AddProductButton extends JButton {
	public AddProductButton(ShopWindow sw, MainWindow w){
		this.setText("Add product");
		this.setSize(new Dimension(50, 20));
		this.addActionListener(e->{
			sw.openProductWindow();
			w.updateContents();
		});
	}
}