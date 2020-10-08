from struct import Struct
from typing import List

from function import MacroDefinition, FunctionTemplate
from sql_types import SqlColumn, SqlType
import os


class Header:
	def __init__(self):
		self.global_includes: List[str] = []
		self.local_includes: List[str] = []
		self.pragmas: List[str] = []
		self.macros_def: List[MacroDefinition] = []
		self.functions: List[FunctionTemplate] = []
		self.structs = []
		self.header_guard = None

	def add_macro(self, macro: MacroDefinition):
		self.macros_def.append(macro)

	def add_pragma(self, pragma: str):
		self.pragmas.append(pragma)

	def add_local_include(self, incl: str):
		self.local_includes.append(incl)

	def add_global_include(self, incl: str):
		self.global_includes.append(incl)

	def add_function(self, func: FunctionTemplate):
		self.functions.append(func)

	def add_struct(self, struct: Struct):
		self.structs.append(struct)

	def set_header_guard(self, guard: str):
		self.header_guard = guard

	def __str__(self):
		assert self.header_guard is not None
		out = ""
		out += "#ifndef {header_guard}\n".format(header_guard=self.header_guard)
		out += "#define {header_guard}\n\n".format(header_guard=self.header_guard)

		out += "".join([f"#pragma {pragma}\n" for pragma in self.pragmas])
		out += "\n"
		out += "".join([str(macro) + "\n" for macro in self.macros_def])
		out += "".join([f"#include <{incl}>\n" for incl in self.global_includes])
		out += "\n"
		out += "".join([f"#include \"{incl}\"\n" for incl in self.local_includes])
		out += "\n"

		out += "\n".join([str(struct) for struct in self.structs])

		for struct in self.structs:
			out += "\n\n".join([func.get_declaration() for func in struct.methods])

		out += "\n\n".join([func.get_declaration() for func in self.functions])

		out += "".join([macro.undef() for macro in self.macros_def])
		out += "\n\n#endif\n"

		return out
