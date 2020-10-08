package beans.stocks;

import entity.Stock;

import java.io.Serializable;
import java.util.List;

public class StocksCalculator implements Serializable {
	public static double getStocksPrice(String stock, Integer count) {
		List<Stock> stockList = new StocksBean().getStocks();
		Stock s = stockList.stream().filter(st -> st.getStockName().equals(stock)).findAny().orElse(null);
		if (s != null) {
			return s.getValue() * count;
		}
		return 0;
	}
}
