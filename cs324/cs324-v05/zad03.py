class VideoSnimak:
	def __init__(self, name, length):
		self.name = name
		self.length = length
		self.rating = 0

	def rate(self, rating):
		self.rating = rating

	def __repr__(self) -> str:
		return "VideoSnimak('{}', '{}', {}*)".format(self.name, self.length, self.rating)


class Film(VideoSnimak):
	def __init__(self, name, length, year, genre, director):
		super().__init__(name, length)
		self.year = year
		self.genre = genre
		self.director = director

	def __repr__(self) -> str:
		return "Film('{}', '{}', {}, '{}', '{}', {}⭐)".format(self.name, self.length, self.year, self.genre,
		                                                      self.director, self.rating)


class Serija(VideoSnimak):
	def __init__(self, name, genre, seasons, episodes_per_season):
		super().__init__(name, None)
		self.seasons = seasons
		self.genre = genre
		self.episodes_per_season = episodes_per_season

	def __repr__(self) -> str:
		return "Serija('{}', '{}', '{}', {}, {}, {}⭐)".format(self.name, self.length, self.genre,
		                                                      self.seasons, self.episodes_per_season, self.rating)


def main():
	blade_runner = Film("Blade Runner", "1:57", "1987", "Sci-Fi", "Ridley Scott")
	blade_runner.rate(4)
	jaws = Film("Jaws", "2:04", "1975", "Thriller", "Steven Spielberg")
	jaws.rate(4)
	burn_notice = Serija("Burn Notice", "Action", 7, 7)
	burn_notice.rate(4)

	video001 = VideoSnimak("Video001", "0:02")

	print(blade_runner)
	print(jaws)
	print(video001)
	print(burn_notice)


if __name__ == '__main__':
	main()
