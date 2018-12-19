package zadatak18;

//		Napisati klasu Sektor koja od atributa ima naziv, adresu, broj zaposlenih, statički atribut koji predstavlja
//		platu zaposlenih. Napisati izvedene klase ITSektor i HRSektor koje imaju različite plate zaposlenima.
//		Napraviti polimorfizam i obezbediti da se ukupne plate zaposlenima računaju drugačije u zavisnosti od
//		sektora. U IT sektoru se ukupne plate računaju po formuli
//		1000000 /𝑏𝑟𝑧𝑎𝑝𝑜𝑠𝑙𝑒𝑛𝑖ℎ ∗ 0.8 + 5000
//		Dok se u HR sektoru ukupne plate računaju po formuli
//		1000000 /𝑏𝑟𝑧𝑎𝑝𝑜𝑠𝑙𝑒𝑛𝑖ℎ ∗ 0.8
//		Napomena: Za svaku od klasa u ovom zadatku treba da ima getere, setere, toString metod kao i
//		konstruktore, prazan, konstruktor polja i konstruktor kopije. Testirati rad klasa iz Main klase.
public class Main {
	public static void main(String[] args){
		ITSector s1 = new ITSector("Example Sector 1", "123 Main St", 20, 1000);
		HRSector s2 = new HRSector("Example Sector 2", "101 Main St", 5, 1000);
		System.out.printf("%s\n", s1.toString());
		System.out.printf("%s\n", s2.toString());
		System.out.printf("$ %.2f\n", s1.getWage());
		System.out.printf("$ %.2f\n", s2.getWage());
		ITSector s3 = new ITSector(s1);
		s3.setWage(1200);
		s3.setEmployees(100);
		System.out.printf("%s\n", s3.toString());
		System.out.printf("$ %.2f\n", s3.getWage());


	}
}

abstract class Sector {
	String name;
	String address;
	int employees;
	double wage;

	public Sector(String name, String address, int employees, double wage) {
		this.name = name;
		this.address = address;
		this.employees = employees;
		this.wage = wage;
	}

	public Sector(String name) {
		this(name, "",0,0);
	}

	public Sector(Sector s) {
		this(s.getName(), s.getAddress(), s.getEmployees(), s.getWage());
	}

	abstract double getWage();

	public void setWage(double wage){
		this.wage = wage;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getEmployees() {
		return employees;
	}

	public void setEmployees(int employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Sector{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", employees=" + employees +
				", wage=" + wage +
				'}';
	}

}


class HRSector extends Sector {
	public HRSector(String name, String address, int employees, double wage) {
		super(name, address, employees, wage);
	}

	public HRSector(HRSector s) {
		super(s);
	}

	@Override
	public double getWage() {
		return 1000000 / super.getEmployees() * 0.8;
	}
}
class ITSector extends Sector {
	public ITSector(String name, String address, int employees, double wage) {
		super(name, address, employees, wage);
	}
	public ITSector(ITSector s){
		super(s);
	}
	@Override
	public double getWage() {
		return 1000000 / super.getEmployees() * 0.8 + 5000;
	}
}

