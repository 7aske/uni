import random

import matplotlib.pyplot as plt


class Warrior:
	def __init__(self, name, health, armor_class, attack_bonus):
		self.name = name
		self.health = health
		self.armorClass = armor_class
		self.attackBonus = attack_bonus

	def roll_for_attack(self):
		return self.attackBonus + random.randint(1, 20)

	def check_hit(self, damage):
		return self.armorClass < damage

	def take_damage(self, damage):
		if self.check_hit(damage):
			self.health -= damage


# @Incomplete
def main():
	black_knight = Warrior("Black Knight", 100, 20, 10)
	king_arthur = Warrior("King Arthur", 90, 24, 12)
	black_knight_attacks = []
	king_arthur_attacks = []
	no_hits = []
	while not (black_knight.health < 0 and king_arthur.health < 0):
		turn = random.randint(0, 1)
		bka = black_knight.roll_for_attack()
		kab = black_knight.roll_for_attack()

		if turn == 1:
			black_knight.take_damage(kab)
		else:
			king_arthur.take_damage(bka)
		black_knight_attacks.append(bka)
		king_arthur_attacks.append(kab)
		no_hits.append(None)

	plt.plot(no_hits, king_arthur_attacks, 'r-', label="Cos talas")
	plt.plot(no_hits, black_knight_attacks, 'k--', label="Sin talas")
	plt.grid()
	plt.show()


if __name__ == '__main__':
	main()
