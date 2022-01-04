import threading
from typing import Optional, Callable, Any, Iterable, Mapping


class Timer(threading.Thread):
	def __init__(self, callback) -> None:
		super().__init__()
		self._stop_event = threading.Event()
		self._tick = 0.01  # one millisecond
		self.time = 0
		self.callback = callback

	def get_time(self):
		return self.time

	def start(self) -> None:
		super().start()
		self.time = 0

	def run(self) -> None:
		while not self._stop_event.is_set():
			self.time += 1
			self.callback()
			self._stop_event.wait(self._tick)

	def join(self, timeout=None):
		self._stop_event.set()
		threading.Thread.join(self, timeout)
