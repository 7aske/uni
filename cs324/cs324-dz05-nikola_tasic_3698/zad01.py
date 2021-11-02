from pprint import pprint


class Document:
	def __init__(self, name, num_words):
		self.name = name
		self.num_words = num_words


class Book(Document):
	current_num = 1

	def __init__(self, name, num_words, author, genre, year):
		super(Book, self).__init__(name, num_words)
		self.key = "lib{}".format(str(self.current_num).rjust(3, "0"))
		self.genre = genre
		self.author = author
		self.year = year
		Book.current_num += 1

	def __repr__(self):
		return "{}: '{}' '{}' '{}'".format(self.key, self.genre, self.author, self.name)


def main():
	books = [
		Book("The Hunger Games", 100, "Suzanne Collins", "Genre", 2008),
		Book("Harry Potter and the Order of the Phoenix ", 100, "J.K. Rowling", "Genre", 2003),
		Book("To Kill a Mockingbird", 100, "Harper Lee", "Genre", 1960),
		Book("Pride and Prejudice", 100, "Jane Austen", "Genre", 1813),
		Book("Twilight", 100, "Stephenie Meyer", "Genre", 2005),
		Book("The Book Thief", 100, "Markus Zusak", "Genre", 2005),
		Book("Animal Farm", 100, "George Orwell", "Genre", 1945),
		Book("The Chronicles of Narnia", 100, "C.S. Lewis", "Genre", 1956),
		Book("The Lord of the Rings", 100, "J.R.R Tolkien", "Genre", 1955),
		Book("The Fault in Our Stars", 100, "John Green", "Genre", 2012),
	]

	books_dict = {book.key: book for book in books}
	pprint(books_dict)


if __name__ == '__main__':
	main()
