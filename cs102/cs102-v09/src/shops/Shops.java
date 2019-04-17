package shops;



import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

public class Shops {
	public static void main(String[] args) {
		ReentrantLock shopLock = new ReentrantLock();
		Stockpile stock = new Stockpile();
		Factory fact = new Factory(stock, shopLock);
		Shop shop = new Shop(stock, shopLock);

		Thread tFact = new Thread(fact);
		Thread tShop = new Thread(shop);
		tFact.start();
		tShop.start();

	}
}

class Shop implements Runnable {
	private Stockpile stock;
	private ReentrantLock lock;

	public Shop(Stockpile stock, ReentrantLock lock) {
		this.stock = stock;
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(1000);
				sellItem();
			}
		} catch (InterruptedException e) {
		}
	}
	private void sellItem() {
		Product itemToSell = this.stock.get();
		if (itemToSell != null) {
			System.out.printf("Shop sold %s for %f\n", itemToSell.getName(), itemToSell.getPrice());
			if (this.lock.isLocked())
				this.lock.unlock();
		} else {
			System.out.println("Nothing to sell");
		}
	}

}

class Factory implements Runnable {
	private Stockpile stock;
	private ReentrantLock lock;
	private Product[] possibleProducts = {new Product("chair", 50), new Product("hammer", 20), new Product("shoes", 100)};

	public Factory(Stockpile stock, ReentrantLock lock) {
		this.stock = stock;
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Product p = createProduct();
				this.lock.lock();
				this.stock.add(p);
				this.lock.unlock();
				Thread.sleep(1500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Product createProduct() {
		System.out.println("Factory created a product\n");
		return new Product(possibleProducts[(int) (Math.random() * possibleProducts.length)]);
	}
}


class Stockpile {
	private Stack<Product> stock = new Stack<>();

	public Stockpile() {
	}

	public void add(Product p) {
		this.stock.push(p);
	}

	public Product get() {
		if (stock.size() > 0) {
			return stock.pop();
		} else {
			return null;
		}
	}
}

class Product {
	private String name;
	private double price;

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Product(Product p) {
		this.name = p.getName();
		this.price = p.getPrice();
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
}