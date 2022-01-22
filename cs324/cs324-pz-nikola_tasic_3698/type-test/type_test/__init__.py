import curses
import os
from type_test.program import Program


def main():
	# TERM variable is needed for curses library to work property.
	# xterm-256color should be a sane default
	if os.environ.get("TERM") is None:
		os.environ["TERM"] = "xterm-256color"
	curses.wrapper(wrapper)


def wrapper(stdscr):
	program = None
	try:
		program = Program(stdscr)
		program.run()
	except KeyboardInterrupt:
		if program is not None:
			program.stop()
