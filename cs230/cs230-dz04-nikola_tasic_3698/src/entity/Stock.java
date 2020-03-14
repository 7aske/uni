package entity;

import java.io.Serializable;

public class Stock implements Serializable {
	public String name;
	public String stockName;
	public double value;

	public Stock(String name, String stockName, double value) {
		this.name = name;
		this.stockName = stockName;
		this.value = value;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Stock{" +
				"name='" + name + '\'' +
				", stockName='" + stockName + '\'' +
				", value=" + value +
				'}';
	}
}