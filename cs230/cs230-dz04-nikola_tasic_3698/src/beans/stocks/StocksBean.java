package beans.stocks;

import entity.Stock;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class StocksBean implements Serializable {
	private List<Stock> stocks = Arrays.asList(
			new Stock("Tesla", "TSLA", 546.62),
			new Stock("Apple", "AAPL", 277.97),
			new Stock("IBM", "IBM", 107.95),
			new Stock("Nvidia", "NVDA", 278.24),
			new Stock("NortonLifeLock", "NLOK", 19.35),
			new Stock("Equifax", "EFX", 157.76)
	);

	public StocksBean() {
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	@Override
	public String toString() {
		return "Stocks{" +
				"beans.stocks=" + stocks +
				'}';
	}
}
