string[] zodiac = {
	"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius",
	"Pisces"
};

void main() {
	string firstName;
	string lastName;
	DateTime dateOfBirth;

	Console.Write("Ime: ");
	firstName = Console.ReadLine()!;
	Console.Write("Prezime: ");
	lastName = Console.ReadLine()!;
	string pattern = "dd/MM/yyyy";
	Console.Write("Datum rodjenja({0}): ", pattern);
	dateOfBirth = DateTime.ParseExact(Console.ReadLine()!, pattern, null);
	string zodiac = getAstrologySign(dateOfBirth);
	Console.WriteLine("{0} {1} -> {2}", firstName, lastName, zodiac);
}

string getAstrologySign(DateTime date) {
	int months = date.Month;
	int days = date.Day;

	switch (months) {
		case 1:
			if (days >= 1 & days <= 19) {
				return zodiac[9];
			} else {
				return zodiac[10];
			}
		case 2:
			if (days >= 1 & days <= 18) {
				return zodiac[10];
			} else {
				return zodiac[11];
			}
		case 3:
			if (days >= 21) {
				return zodiac[0];
			} else {
				return zodiac[11];
			}
		case 4:
			if (days >= 1 & days <= 19) {
				return zodiac[0];
			} else {
				return zodiac[1];
			}
		case 5:
			if (days >= 1 & days <= 20) {
				return zodiac[1];
			} else {
				return zodiac[2];
			}
		case 6:
			if (days >= 1 & days <= 21) {
				return zodiac[2];
			} else {
				return zodiac[3];
			}

		case 7:
			if (days >= 1 & days <= 22) {
				return zodiac[3];
			} else {
				return zodiac[4];
			}
		case 8:
			if (days >= 1 & days <= 22) {
				return zodiac[4];
			} else {
				return zodiac[5];
			}
		case 9:
			if (days >= 1 & days <= 22) {
				return zodiac[5];
			} else {
				return zodiac[6];
			}
		case 10:
			if (days >= 1 & days <= 22) {
				return zodiac[6];
			} else {
				return zodiac[7];
			}

		case 11:
			if (days >= 1 & days <= 21) {
				return zodiac[7];
			} else {
				return zodiac[8];
			}
		case 12:
			if (days >= 1 & days <= 21) {
				return zodiac[8];
			} else {
				return zodiac[9];
			}
	}

	throw new Exception("Invalid date");
}

main();