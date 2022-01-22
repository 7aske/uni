import math
from datetime import datetime


class Pizza:
	def __init__(self, name, size, price) -> None:
		self.price = price
		self.size = size
		self.name = name

	def area(self):
		return self.size / 2 * math.sqrt(math.pi)

	def price_per_cm2(self):
		return self.price / self.area()

	def __repr__(self) -> str:
		return "{} {} {}".format(self.name, self.size, self.price)


def save_pizzas(pizza_list):
	with open("pizzas.txt", "w") as file:
		file.write(datetime.now().__str__() + "\n")
		for pizza in pizza_list:
			file.write(
				'Pizza {}, {} cm, {}\n'.format(pizza.name, pizza.size,
				                               pizza.price))


def best_pizza(pizza_list):
	pizza_list.sort(key=lambda x: x.price_per_cm2())
	return pizza_list[0]


def main():
	pizza1 = Pizza("Capricciosa", 50, 1200)
	pizza2 = Pizza("Quattro Formaggi", 50, 1500)
	pizza3 = Pizza("Margherita", 50, 1000)
	best = best_pizza([pizza1, pizza2, pizza3])
	print('Najbolji odnos cena po povrsini ima pizza {ime} od {precnik} cm, i cene od {cena}'.format(ime=best.name,
	                                                                                                 precnik=best.size,
	                                                                                                 cena=best.price))
	save_pizzas([pizza1, pizza2, pizza3])


if __name__ == '__main__':
	main()
