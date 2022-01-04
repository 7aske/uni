import curses
import re
import threading
from statistics import mean

from type_test.quotes.Quotes import Quotes
from type_test.timer import Timer

TEXT_POS = (2, 0)
HEADER_POS = (0, 0)


class Colors:
	"""
	Class representing valid color pairs for use in
	"""
	# @formatter:off
	__PAIR_RED     = 71
	__PAIR_GREEN   = 72
	__PAIR_ERROR   = 73
	__PAIR_HEADER  = 74
	__PAIR_TITLE   = 75
	__COLOR_TITLE  = 105
	# @formatter:on

	# Must not be instantiated before setting up curses
	def __init__(self) -> None:
		# @formatter:off
		curses.init_color(self.__COLOR_TITLE, 432, 488, 548)
		curses.init_pair(self.__PAIR_RED,    curses.COLOR_RED,   curses.COLOR_BLACK)
		curses.init_pair(self.__PAIR_GREEN,  curses.COLOR_GREEN, curses.COLOR_BLACK)
		curses.init_pair(self.__PAIR_ERROR,  curses.COLOR_BLACK, curses.COLOR_RED)
		curses.init_pair(self.__PAIR_HEADER, curses.COLOR_BLACK, curses.COLOR_MAGENTA)
		curses.init_pair(self.__PAIR_TITLE,  self.__COLOR_TITLE, curses.COLOR_BLACK)
		self.RED    = curses.color_pair(self.__PAIR_RED)
		self.GREEN  = curses.color_pair(self.__PAIR_GREEN)
		self.ERROR  = curses.color_pair(self.__PAIR_ERROR)
		self.HEADER = curses.color_pair(self.__PAIR_HEADER)
		self.TITLE  = curses.color_pair(self.__PAIR_TITLE)
		# @formatter:on


class Program:
	def __init__(self, stdscr) -> None:
		self.stdscr = stdscr
		self.colors = Colors()
		self.quotes = Quotes.load()
		self.running = False
		self.started = False
		self.typed = ""
		self.selected_quote = None
		# Cursor position
		self.cur_pos = TEXT_POS
		self.size = self.stdscr.getmaxyx()
		self.timer = None
		self.stats = (0, 0)  # wps, cps
		self.time = (0, 0)  # secs, millis
		self.lock = threading.Lock()
		# List of previous attempt wpm's
		self.previous_attempts = []
		# Save value of previous attempts average so that we don't have
		# to calculate every re-render
		self.avg_cached = 0

	def restart(self):
		self.stdscr.clear()
		self.typed = ""
		self.selected_quote = self.quotes.random()

		# we manually call one render so that the user
		# can see which quote he got
		self.render_header()
		self.render()

		while not self.started:
			c = self.stdscr.getch()
			# TODO: duplicate code
			if 32 <= c <= 126:
				self.typed += chr(c)
				self.started = True
			# FIXME: duplicate code:
			elif c == curses.KEY_RESIZE:
				self.handle_resize()
			elif c == curses.KEY_LEFT or c == curses.KEY_RIGHT:
				self.restart()

		self.running = True
		self.start_timer()

	def run(self):
		self.restart()
		self.loop()

	# Main loop
	def loop(self):
		while self.running:
			self.render()
			self.check_win()
			self.refresh_screen()
			self.read_input()

	# We call this after rendering the part of the screen which
	# is not the position of the next character to be typed by the user.
	def update_cursor(self):
		self.stdscr.move(*self.cur_pos)

	def render_header(self):
		with self.lock:
			seconds, millis = self.time
			wpm, cps, avg = self.get_stats()
			self.stdscr.move(*HEADER_POS)
			self.stdscr.clrtoeol()
			# Draw background for the header
			self.stdscr.chgat(*HEADER_POS, self.size[1], self.colors.HEADER)
			header_string = "{:.2f} wpm {:.2f} cps {}.{:02d}s {:.2f} avg wpm".format(wpm, cps, seconds, millis, avg)
			# Draw current data
			self.stdscr.addstr(*HEADER_POS, header_string, self.colors.HEADER)
			# Return the cursor to its original position
			self.update_cursor()

	def render(self):
		def inc_row_col(y, x):
			# This is done to prevent us from running off of the screen.
			if x == self.size[1] - 1:
				y += 1
				x = TEXT_POS[1]  # in case we have some padding
			else:
				x += 1
			return y, x

		with self.lock:
			# Pointers used to track current cursor position to be written on the screen
			cur_y, cur_x = TEXT_POS
			# We first manually print the text so that we can find the 'y' position for the title.
			# Title should be rendered below the text
			for selected in self.selected_quote.text:
				self.stdscr.addch(cur_y, cur_x, ord(selected))

				cur_y, cur_x = inc_row_col(cur_y, cur_x)

			errored = False
			color = self.colors.GREEN
			# Save 'y' for printing title
			title_y = cur_y
			# Reset the position so that we can print the user typed text
			cur_y, cur_x = TEXT_POS

			for typed, selected in zip(self.typed, self.selected_quote.text):
				valid = typed == selected
				if not valid:
					# We don't reset errored flag unless we re-render the whole text in
					# next frame. This way we show errors since the first occurrence of
					# the mistyped letter.
					errored = True
				if errored:
					color = self.colors.ERROR
				self.stdscr.addch(cur_y, cur_x, ord(selected), color)

				cur_y, cur_x = inc_row_col(cur_y, cur_x)

			title = "{}, {}".format(self.selected_quote.author, self.selected_quote.title)
			self.stdscr.addstr(TEXT_POS[0] + title_y + 1, TEXT_POS[1], title, self.colors.TITLE)

			# After drawing on the screen we need to set cur_pos to allow
			# update_cursor to move it to the valid position
			self.cur_pos = (cur_y, cur_x)
			self.update_cursor()

	def read_input(self):
		c = self.stdscr.getch()
		if c == curses.KEY_BACKSPACE:
			if len(self.typed) > 0:
				self.typed = self.typed[:-1]
		elif c == 4:  # CTRL+D
			self.restart()
		elif c == curses.KEY_EXIT:
			self.restart()
		# FIXME: can this be better?
		elif 32 <= c <= 126:
			self.typed += chr(c)

		self.update_stats()

	# Calculate typing stats. We should call this when we want up-to-date data
	def get_stats(self):
		secs = self.time[0]
		if secs > 0:
			return self.stats[0] / secs * 60, self.stats[1] / secs, self.avg_cached
		return 0, 0, self.avg_cached

	# Instead of calculating stats every time we re-render the header
	# we update them only when a new character is typed which should
	# lead to better performance. Need to keep in mind that we cannot calculate
	# actual values here because typing occurs way less often than rendering
	# the header.
	def update_stats(self):
		words = len(re.split(r"\s+", self.typed))
		self.stats = (words, len(self.typed))

	def check_win(self):
		if self.selected_quote.text == self.typed:
			self.started = False
			self.stop_timer()
			self.previous_attempts.append(self.get_stats()[0])
			self.avg_cached = mean(self.previous_attempts)
			# Re-render header to show new updated average data
			self.render_header()
			# Wait for any key then restart
			self.stdscr.getch()
			# Don't forget to call restart
			self.previous_attempts.append(self.get_stats()[0])
			self.restart()

	def handle_resize(self):
		self.size = self.stdscr.getmaxyx()
		# Re-render after resizing
		self.stdscr.clear()
		self.render_header()
		self.render()
		self.refresh_screen()

	# Method called by the timer thread used for re-rendering
	# the header
	def __timer_callback(self):
		# We avoid initializing the values to 0
		# in order to allow showing previous time after
		# the timer stops
		seconds, millis = self.time
		if self.timer is not None:
			elapsed = self.timer.get_time()
			seconds = elapsed // 100
			millis = elapsed - seconds * 100
		# We save the data
		self.time = (seconds, millis)
		self.render_header()
		self.refresh_screen()

	# Stop the timer by joining its thread
	def stop_timer(self):
		if self.timer is not None:
			self.timer.join()

	def start_timer(self):
		if self.timer is not None:
			self.timer.join()
		self.timer = Timer(callback=self.__timer_callback)
		self.timer.start()

	# Stop timer and the main loop
	def stop(self):
		self.running = False
		self.stop_timer()

	def refresh_screen(self):
		with self.lock:
			self.stdscr.refresh()
