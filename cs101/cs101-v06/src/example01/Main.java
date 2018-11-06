package example01;
public class Main {
	public static void main(String[] args) {
		Manufacturer intel = new Manufacturer("Intel", "CPU");
		CPU cpu0 = new CPU("Core i5 8300H", intel,4,8,2.6);
		System.out.printf("%s\n",cpu0.toString());
	}
}


