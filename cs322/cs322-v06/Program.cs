using System;

namespace cs322_v06 {
	internal class Program {
		public static void Main(string[] args) {
		}
	}

	abstract class Account {
		protected float balance;

		public abstract void Add(float value);
	}

	class DinAccount : Account {
		public override void Add(float value) {
			balance += value;
		}
	}

	class EurAccount : Account {
		public override void Add(float value) {
			balance += value * 117f;
		}
	}
	
	sealed class BlockedEurAccount : EurAccount {
		public override void Add(float value) {
			throw new Exception("Account Blocked");
		}
	}
}