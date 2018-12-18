package Zadatak03;

public class Zadatak03 {
	public static void main(String[] args) {

	}
}

abstract class Payment {
	private double balance;

	public Payment(double b) {
		this.balance = b;
	}

	abstract void pay(double a);

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}

class CardPayment extends Payment {
	public CardPayment(double b) {
		super(b);
	}

	public CardPayment() {
		super(0);
	}

	@Override
	void pay(double a) {
		double bal = super.getBalance();
		if (bal > a) {
			super.setBalance(bal - a);
		}
	}
}

class CashPayment extends Payment {
	public CashPayment(double b) {
		super(b);
	}

	public CashPayment() {
		super(0);
	}

	@Override
	void pay(double a) {
		double bal = super.getBalance();
		if (bal > a) {
			super.setBalance(bal - a);
		}
	}
}