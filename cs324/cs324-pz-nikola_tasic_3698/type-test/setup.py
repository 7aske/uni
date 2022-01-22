from setuptools import setup

setup(
	name='type-test',
	version='1.0',
	packages=[
		'type_test',
		'type_test/program',
		'type_test/quotes',
		'type_test/timer',
	],
	package_dir={"type_test": "type_test"},
	package_data={
		"type_test": [
			"data/examples.json.gz",
		]
	},
	url='https://github.com/7aske/type-test',
	license='GNU GPL',
	author='nik',
	author_email='nik@7aske.com',
	description='Typeracer',
	install_requires=[
	],
	entry_points={
		"console_scripts": ["type-test=type_test:main"]
	}
)
