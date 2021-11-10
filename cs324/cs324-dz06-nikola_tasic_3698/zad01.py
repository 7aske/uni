import csv

courses = {
	'CS322': {'lectures_start': '14:50h', 'practice_start': '17:20h'},
	'CS225': {'lectures_start': '15:40h', 'practice_start': '18:10h'},
	'CS324': {'lectures_start': '09:50h', 'practice_start': '12:20h'},
	'IT381': {'lectures_start': '16:30h', 'practice_start': '19:00h'}
}


def main():
	with open("schedule.csv", "w") as file:
		file = csv.writer(file)
		for course, time in courses.items():
			file.writerow([course, time['lectures_start'], time['practice_start']])


if __name__ == '__main__':
	main()
