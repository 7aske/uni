package zadatak08.gui;

import zadatak08.gui.dims.Dims;
import zadatak08.shop.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class ProductWindow extends JFrame {
	static Dims dims = new Dims();
	private Shop shop;
	private ShopEditWindow shopEditWindow;
	private JPanel panel = new JPanel();
	private JTextField productBrandInput = new JTextField();
	private JTextField productNameInput = new JTextField();
	private JTextField productPriceInput = new JTextField();
	private JTextField productStockInput = new JTextField();
	private JTextField productBarcodeInput = new JTextField();

	private AddButton addButton;
	private CancelButton cancelButton;

	public ProductWindow(Shop s, ShopEditWindow sw) {
		this.shop = s;
		this.shopEditWindow = sw;
		this.setDefaultValues();

		this.panel.add(this.productBrandInput);
		this.panel.add(this.productNameInput);
		this.panel.add(this.productPriceInput);
		this.panel.add(this.productStockInput);
		this.panel.add(this.productBarcodeInput);

		this.addButton  = new AddButton(this);
		this.panel.add(addButton);

		this.cancelButton = new CancelButton(this);
		this.panel.add(this.cancelButton);

		this.add(panel);
		this.setSize(dims.windowDim);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	void setDefaultValues() {
		Dimension inputDim = new Dimension(400, 20);
		this.productBarcodeInput.setPreferredSize(inputDim);
		this.productBrandInput.setPreferredSize(inputDim);
		this.productNameInput.setPreferredSize(inputDim);
		this.productPriceInput.setPreferredSize(inputDim);
		this.productStockInput.setPreferredSize(inputDim);

		this.productBarcodeInput.setText("Barcode");
		this.productBrandInput.setText("Brand");
		this.productNameInput.setText("Name");
		this.productPriceInput.setText("100.00");
		this.productStockInput.setText("0");
	}

	void addProduct() {
		Product product = new Product(this.productBrandInput.getText(), this.productNameInput.getText(), Double.parseDouble(this.productPriceInput.getText()), this.productBarcodeInput.getText(), Integer.parseInt(this.productStockInput.getText()));
		this.shop.addProduct(product);
	}

	ShopEditWindow getShopEditWindow(){
		return this.shopEditWindow;
	}
}

class AddButton extends JButton {
	static Dims dims = new Dims();
	public AddButton(ProductWindow pw) {
		this.setText("Add");
		this.setSize(new Dimension(dims.buttonDim));
		this.addActionListener(e -> {
			pw.addProduct();
			pw.getShopEditWindow().updateContents();
			pw.dispatchEvent(new WindowEvent(pw, WindowEvent.WINDOW_CLOSING));
		});
	}
}
class CancelButton extends JButton {
	static Dims dims = new Dims();
	public CancelButton(ProductWindow pw){
		this.setText("Cancel");
		this.setSize(dims.buttonDim);
		this.addActionListener(e->{
			pw.getShopEditWindow().updateContents();
			pw.dispatchEvent(new WindowEvent(pw, WindowEvent.WINDOW_CLOSING));
		});
	}
}