package zadatak05;



//Zadatak 18
//Ispisati sve prirodne brojeve manje od N koji su jednaki sumi kvadrata dvostrukih faktorijela
//svojih cifara
public class Zadatak05 {
	public static void main(String[] args) {
		int n = 10000000;
		for (int i = 0; i < n; i++) {
			if (whatever(i)){
				System.out.println(i);
			}
		}
	}
	public static long fact(int a) {
		if (a == 0) return 1;
		else return a * fact(a - 1);
	}
	public static boolean whatever(int n){
		long sum = 0;
		int num = n;
		while (num > 0 ) {
			int dig = num % 10;
			long f = fact(dig);
			sum += 2 * f * f;
			num /= 10;
		}
		System.out.printf("%10d %10d\n", sum, n);
		return sum == n;
	}
}
