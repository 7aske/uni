from typing import List


class FunctionArgument:
	def __init__(self, arg_type: str, arg_name: str):
		self.arg_type = arg_type
		self.arg_name = arg_name


class MacroDefinition:
	def __init__(self, name: str, macro: str):
		self.name = name
		self.macro = macro

	def __str__(self):
		return "\t#define {name} {macro}\n".format(name=self.name, macro=self.macro)

	def undef(self):
		return "\t#undef {name}\n".format(name=self.name)


class FunctionTemplate:
	def __init__(self, name: str, ret_type: str, arguments: List[FunctionArgument]):
		self.name = name
		self.ret_type = ret_type
		self.arguments = arguments
		self.macro_def: List[MacroDefinition] = []
		self.body = []

	def set_name(self, name: str):
		self.name = name

	def set_body(self, blocks: List[str]):
		self.body = blocks

	def add_body(self, blocks: List[str]):
		for block in blocks:
			self.add_block(block)

	def add_block(self, block: str):
		formatted = []
		block = block.replace("\t", "")
		block = block.lstrip(" ")
		tabcount = 1
		for line in block.split("\n"):
			if '}' in line:
				tabcount -= 1
			line = "\t" * tabcount + line
			if '{' in line:
				tabcount += 1

			formatted.append(line)

		self.body.append("\n".join(formatted))

	def add_argument(self, argument: FunctionArgument):
		self.arguments.append(argument)

	def set_arguments(self, arguments: List[FunctionArgument]):
		self.arguments = arguments

	def get_arguments_str(self):
		return ", ".join([arg.arg_type + " " + arg.arg_name for arg in self.arguments])

	def set_ret_type(self, ret_type: str):
		self.ret_type = ret_type

	def set_macro_def(self, defs: List[MacroDefinition]):
		self.macro_def = defs

	def add_macro_def(self, macro: MacroDefinition):
		self.macro_def.append(macro)

	def add_macro_defs(self, defs: List[MacroDefinition]):
		self.macro_def += defs

	def get_declaration(self):
		return "{return_type} {name}({arguments});".format(return_type=self.ret_type, name=self.name,
		                                                   arguments=self.get_arguments_str())

	def __str__(self):
		out = "\n\n/* Generated function */\n"
		out += "{return_type} {name}({arguments}) {{\n".format(return_type=self.ret_type, name=self.name,
		                                                      arguments=self.get_arguments_str())
		out += "".join([macro.__str__() for macro in self.macro_def])
		out += "\n\n".join(block for block in self.body)
		out += "\n\n"
		out += "".join([macro.undef() for macro in self.macro_def])
		out += "}\n"
		return out
