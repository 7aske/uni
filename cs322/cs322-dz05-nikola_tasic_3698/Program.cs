using System;

namespace cs322_dz05_nikola_tasic_3698 {
	internal class Program {
		public static void Main(string[] args) {
			Zad01();
			Zad02();
		}

		private static void Zad01() {
			Console.Write("Broj: ");
			string numStr = Console.ReadLine();
			Console.Write("Stepen: ");
			string expStr = Console.ReadLine();
			if (long.TryParse(numStr, out long num) && long.TryParse(expStr, out long exp)) {
				long res = Power(num, exp);
				Console.WriteLine("Rezultat je {0}", res);
			}
		}

		private static void Zad02() {
			Visitor visitor1 = new Visitor();
			visitor1.FirstName = VisitorHelper.GetRandStr();
			visitor1.LastName = VisitorHelper.GetRandStr();
			visitor1.TicketNumber = VisitorHelper.GetRandInt();
			
			Visitor visitor2 = new Visitor();
			visitor2.FirstName = VisitorHelper.GetRandStr();
			visitor2.LastName = VisitorHelper.GetRandStr();
			visitor2.TicketNumber = VisitorHelper.GetRandInt();
			
			Console.WriteLine(visitor1);
			Console.WriteLine(visitor2);
		}

		private static long Power(long num, long exp) {
			if (num == 0)
				throw new ArgumentException("Broj ne sme da bude manji od nule");

			if (exp < 0)
				throw new ArgumentException("Stepen mora da bude veci ili jednak nuli");

			return (long) Math.Pow(num, exp);
		}
	}

	internal class Visitor {
		public string FirstName { get; set; }
		public string LastName { get; set; }
		public int TicketNumber { get; set; }

		public override string ToString() {
			return $"{FirstName} {LastName} {TicketNumber}";
		}
	}

	internal static class VisitorHelper {
		public static string GetRandStr() {
			string res = "";
			Random random = new Random();
			for (int i = 0; i < 8; i++)
				res += (char) random.Next('a', 'z');

			return res.Substring(0, 1).ToUpper() + res.Substring(1);
		}

		public static int GetRandInt() {
			Random random = new Random();
			return random.Next(1, 200);
		}
	}
}