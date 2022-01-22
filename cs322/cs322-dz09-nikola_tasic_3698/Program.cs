void zad01() {
	int sum = 0;
	for (int i = 0; i <= 3000; i++) {
		sum += i;
	}

	Console.WriteLine("Zad01 result: {0}", sum);
}

void zad02() {
	int count = 100;
	int sum = 0;
	int i = 1;
	while (count-- > 0) {
		if (i % 2 == 0) {
			sum += i;
		}

		i++;
	}

	Console.WriteLine("Zad02 result: {0}", sum);
}

void zad03() {
	int sum = 0;
	for (int i = 21; i < 99; i++) {
		if (i % 2 == 0) {
			sum += i;
		}
	}

	Console.WriteLine("Zad03 result: {0}", sum);
}

// na linux-u sam nemam windows forme
void zad04() {
	int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2};
	int len = arr.Length;

	// suma
	int sum = arr.Sum();
	Console.WriteLine("Zad04 sum result: {0}", sum);

	// avg
	float avg = sum / (float) len;
	Console.WriteLine("Zad04 avg result: {0}", avg);

	// ispis elemenata
	foreach (int e in arr) {
		Console.Write("{0} ", e);
	}

	Console.WriteLine();

	// zadatak 05 je takodje ovde da bih koristio isti niz

	// sortiramo arr
	Array.Sort(arr);
	int min = arr[0];
	int max = arr[len - 1];

	Console.WriteLine("Zad05 min result: {0}", min);
	Console.WriteLine("Zad05 max result: {0}", max);
}


void main() {
	zad01();
	zad02();
	zad03();
	zad04();
}

main();