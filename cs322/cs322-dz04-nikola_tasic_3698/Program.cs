using System;

namespace cs322_dz04_nikola_tasic_3698 {
	internal class Program {
		public static void Main(string[] args) {
			Zad01(100);
			Zad02();
			Zad03();
		}

		private static void Zad01(int n) {
			int sum = 0;
			for (int i = 1; i < n; i++)
				if (n % i == 0)
					sum += i;

			Console.WriteLine(sum);
		}

		private static void Zad02() {
			int sum = 0;
			for (int i = 8; i <= 16; i++) {
				sum += i;
			}

			sum *= 2;

			Console.WriteLine(Reverse(sum.ToString()));
		}

		private static string Reverse(string s) {
			char[] charArray = s.ToCharArray();
			Array.Reverse(charArray);
			return new string(charArray);
		}

		private static void Zad03() {
			string input = Console.In.ReadLine();
			bool isValid = true;
			if (int.TryParse(input, out int num)) {
				if (num >= 1_000_000_000) {
					Console.WriteLine("Broj nije validan");
					return;
				}

				string numAsStr = num.ToString();
				for (int i = 0; i < numAsStr.Length - 1; i++) {
					if (numAsStr[i] >= numAsStr[i + 1]) {
						isValid = false;
						break;
					}
				}
			} else {
				Console.WriteLine("Broj nije validan");
				return;
			}

			Console.WriteLine(isValid ? "{0} ima strogo rastuce cifre" : "{0} nema strogo rastuce cifre", num);
		}
	}
}