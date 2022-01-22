using System;
using System.ComponentModel;

namespace cs322_v05 {
	abstract class Animal {
		public string Name {
			get {
				return name;
			}
			protected set {
				name = value;
			}
		}

		private string name;

		public Animal() {
		}

		public abstract void MakeSound();

		~Animal() {
			Console.WriteLine("Animal ~ called");
		}
	}

	class Dog : Animal {
		public Dog() {
			Name = "Rex";
		}


		public override void MakeSound() {
			Console.WriteLine("Dog says woof");
		}

		~Dog() {
			Console.WriteLine("Dog ~ called");
			Console.WriteLine("Dog name value was: " + Name);
		}
	}

	class Cat : Animal {
		public Cat() {
			Name = "Tom";
		}

		public override void MakeSound() {
			Console.WriteLine("Cat says meow");
		}

		~Cat() {
			Console.WriteLine("Cat ~ called");
			Console.WriteLine("Cat name value was: " + Name);
		}
	}

	internal class Program {
		public static void Main(string[] args) {
			Animal cat = new Cat();
			Animal dog = new Dog();
			cat.MakeSound();
			dog.MakeSound();
		}
	}
}