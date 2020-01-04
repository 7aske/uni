from typing import List

from sql_types import SqlType, SqlColumn
from function import FunctionTemplate, MacroDefinition


class Prop:
	def __init__(self, name: str, proptype: SqlColumn):
		self.name = name
		self.proptype = proptype


class Struct:
	def __init__(self, name: str, columns: List[SqlColumn]):
		# assert columns[0].proptype == SqlType.PK_LONG

		self.members = columns
		self.name = name
		self.typedef_name = name.upper()
		self.enum_name = name.upper() + "_E"
		self.methods: List[FunctionTemplate] = []

	def __getitem__(self, key) -> SqlColumn:
		return self.members[key]

	def __setitem__(self, key, value):
		self.members[key] = value

	def __str__(self):
		out = ""
		out += "struct {name} {{\n".format(name=self.name)
		out += "".join(["\t" + self.format_prop(prop) + "\n" for prop in self.members])
		out += "};\n\n"
		out += "typedef struct {name} {typedef};\n\n".format(name=self.name, typedef=self.typedef_name)

		return out

	def __repr__(self) -> str:
		out = ""
		for prop in self.members:
			out += "{:10} : {}\n".format(prop.name, prop.proptype)
		return out

	@staticmethod
	def format_prop(prop: SqlColumn):
		"""
		Formats the struct memeber for generating header files.
		"""
		if prop.proptype == SqlType.VARCHAR:
			return "char {name}[{size}];".format(name=prop.name, size=prop.size)
		elif prop.proptype == SqlType.TEXT:
			return "char {name}[{size}];".format(name=prop.name, size=prop.size)
		elif prop.proptype == SqlType.PK_LONG:
			return "uint {name};".format(name=prop.name)
		elif prop.proptype == SqlType.FK_LONG:
			return "struct {name}* {name};".format(name=prop.name.replace("_id", "").replace("id_", ""))
		elif prop.proptype == SqlType.LONG:
			return "uint {name};".format(name=prop.name)
		elif prop.proptype == SqlType.DATE:
			return "struct tm {name};".format(name=prop.name)
		else:
			msg = f"SQL type not handled '{prop}'"
			assert False, msg

	def col_count(self):
		"""
		Returns the number of columns.
		"""
		return len(self.members)

	def param_count(self):
		"""
		Returns the number of columns excluding Primary Key.
		"""
		return len(self.members) - 1

	def get_col_buffer_definitions(self):
		"""
		Defines buffers which are used for SQL Result set binding.
		"""
		out = "/* Generated using get_col_buffer_definitions()*/\n"
		out += """unsigned long lengths[RES_COL_COUNT];
		my_bool is_null[RES_COL_COUNT];
		my_bool error[RES_COL_COUNT];\n"""
		for prop in self.members:
			out += "\t"
			if prop.proptype in [SqlType.VARCHAR, SqlType.DATE, SqlType.TEXT]:
				out += "char {col}_buffer[{size}];\n".format(col=prop.name, size=prop.size)
			elif prop.proptype in [SqlType.LONG, SqlType.FK_LONG, SqlType.PK_LONG]:
				out += "uint {col}_buffer;\n".format(col=prop.name)
		return out

	def get_buffer_bindings(self):
		"""
		Generates binding for every struct member in the query. Buffers to which members are bound
		must be defined with get_col_buffer_definitions().
		"""
		out = "/* Generated using get_buffer_bindings()*/\n"
		out += "MYSQL_BIND param[RES_COL_COUNT];\n"
		out += "memset(param, 0, sizeof(param));\n"
		for i, prop in enumerate(self.members):
			out += self.bind_prop_buffer(i, prop)
		return out

	@staticmethod
	def bind_prop_buffer(index, prop):
		"""
		Generates code that binds parameter structs memembers to predefined stack allocated buffers.
		Buffers must be defined with get_col_buffer_definitions().
		"""
		out = """
				/* {type} COLUMN */
				param[{index}].buffer_type = {mysql_type};
				param[{index}].buffer = &{name}_buffer;
				param[{index}].is_null = &is_null[{index}];
				param[{index}].length = &lengths[{index}];
				param[{index}].error = &error[{index}];
				"""
		reg_type = None
		mysql_type = None
		if prop.proptype in [SqlType.VARCHAR, SqlType.TEXT]:
			reg_type = "STRING"
			mysql_type = "MYSQL_TYPE_STRING"
			out += "param[{index}].buffer_length = {len};\n".format(index=index, len=prop.size)

		elif prop.proptype == SqlType.DATE:
			reg_type = "DATE"
			mysql_type = "MYSQL_TYPE_STRING"
			out += "param[{index}].buffer_length = BUFFER_SIZE;\n"

		elif prop.proptype in [SqlType.LONG, SqlType.FK_LONG, SqlType.PK_LONG]:
			reg_type = "INTEGER"
			mysql_type = "MYSQL_TYPE_LONG"
		else:
			msg = f"SQL type not handled '{prop}'"
			assert False, msg

		assert reg_type is not None
		assert mysql_type is not None

		return out.format(index=index, type=reg_type, mysql_type=mysql_type, name=prop.name)

	def col_fetch(self):
		"""
		Generates part of the code responsible for looping over results fetched from
		executing the SQL statement. Respective buffers must be defined and bound.
		"""
		cols = ""

		for i, prop in enumerate(self.members):
			if prop.proptype in [SqlType.VARCHAR, SqlType.TEXT]:
				cols += """
					if (is_null[{index}]) {{
						strcpy((({name}*) row->data)->{col}, "NULL");
					}} else {{
						strncpy((({name}*) row->data)->{col}, {col}_buffer, lengths[{index}]);
					}}""".format(index=i, col=prop.name, name=self.typedef_name)
			elif prop.proptype == SqlType.DATE:
				cols += """
					if (is_null[{index}]) {{
						// strcpy((({name}*) row->data)->{col}, "NULL");
					}} else {{
						mysql_timecpystr(&(({name}*) row->data)->{col}, {col}_buffer);
					}}""".format(index=i, col=prop.name, name=self.typedef_name)
			elif prop.proptype in [SqlType.LONG, SqlType.PK_LONG]:
				cols += """
					if (is_null[{index}]) {{
						(({name}*) row->data)->{col} = 0;
					}} else {{
						(({name}*) row->data)->{col} = {col}_buffer;
					}}""".format(index=i, col=prop.name, name=self.typedef_name)
			elif prop.proptype == SqlType.FK_LONG:
				cols += """
					if (is_null[{index}]) {{
						(({name}*) row->data)->{col_name} = NULL;
					}} else {{
						(({name}*) row->data)->{col_name} = {col_name}_find_by_id(conn, {col}_buffer);
					}}""".format(index=i, col=prop.name, col_name=prop.name.replace("_id", "").replace("id_", ""),
				                 name=self.typedef_name)
			else:
				msg = f"SQL type not handled '{prop}'"
				assert False, msg
		return """
			/* Generated using col_fetch()*/
			while (!mysql_stmt_fetch(stmt)) {{
				res->count++;
				row = calloc(1, sizeof(struct sql_result_row));
				if (res->results == NULL) {{
					res->results = row;
				}} else {{
					curr = res->results;
					while (curr->next != NULL) {{
						curr = curr->next;
					}}
					curr->next = row;
				}}
				row->data = calloc(1, sizeof({struct_name}));
				{cols}
			}}""".format(cols=cols, struct_name=self.name.upper())

	def get_col_param_buffers(self):
		"""
		Generates buffers for all available struct members.
		"""
		out = "/* Generated using get_col_param_buffers() */\n"
		for i, prop in enumerate(self.members):
			if i == 0:
				continue
			out += self.col_param_from_prop(i - 1, prop, self.name)
		return out

	def get_col_param_buffer(self, props: List[str]):
		"""
		Generates buffers for the selected struct members.
		"""
		members = []
		for mem in self.members:
			if mem.name in props:
				members.append(mem)

		out = "/* Generated using  get_col_param_buffers() */\n"
		out += "MYSQL_BIND param[PARAM_COUNT];\n"
		out += "memset(param, 0, sizeof(param));\n"
		for i, prop in enumerate(members):
			out += self.col_param_from_prop(i, prop, self.name)
		return out

	@staticmethod
	def col_param_from_prop(num, prop, name):
		"""
		Allocates respective param buffers that store struct data used in the SQL Query.
		Buffers are used in queries such as 'UPDATE' or 'INSERT'

		Example:

		/* STRING PARAM */
		param[1].buffer = malloc(name_len);
		param[1].buffer_type = MYSQL_TYPE_STRING;
		param[1].buffer_length = name_len;
		strncpy(param[1].buffer, libraryT->name, name_len);

		Make sure to free buffers using col_param_buffer_free().
		"""
		out = """
				/* {type} PARAM */
				param[{index}].buffer = malloc({buffer_size});
				param[{index}].buffer_type = {mysql_type};
				"""
		reg_type = None
		mysql_type = None
		buffer_size = None

		if prop.proptype in [SqlType.VARCHAR, SqlType.TEXT]:
			reg_type = "STRING"
			mysql_type = "MYSQL_TYPE_STRING"
			buffer_size = "{col}_len".format(col=prop.name)
			out += """param[{index}].buffer_length = {col}_len;
				strncpy(param[{index}].buffer, {name}T->{col}, {col}_len);"""

		elif prop.proptype in [SqlType.LONG, SqlType.PK_LONG]:
			reg_type = "INTEGER"
			mysql_type = "MYSQL_TYPE_LONG"
			buffer_size = "sizeof(uint)"
			out += "memcpy(param[{index}].buffer, &{name}T->{col}, {buffer_size});"

		elif prop.proptype == SqlType.FK_LONG:
			reg_type = "INTEGER"
			mysql_type = "MYSQL_TYPE_LONG"
			buffer_size = "sizeof(uint)"
			out += "memcpy(param[{index}].buffer, &{name}T->{col_fk}->{col}, {buffer_size});"

		elif prop.proptype == SqlType.DATE:
			reg_type = "DATE"
			mysql_type = "MYSQL_TYPE_DATE"
			buffer_size = "{}".format(prop.size)
			out += "mysql_timecpy(param[{index}].buffer, &{name}T->{col});"
		else:
			msg = f"SQL type not handled '{prop}'"
			assert False, msg

		assert reg_type is not None
		assert mysql_type is not None
		assert buffer_size is not None

		return out.format(index=num, type=reg_type, mysql_type=mysql_type, col=prop.name, name=name,
		                  buffer_size=buffer_size, col_fk=prop.name.replace("_id", "").replace("id_", ""))

	def col_param_buffer_free(self, num: int = None):
		"""
		Frees allocated param buffers after use.
		"""
		out = "/* Generated using col_param_buffer_free() */\n"
		if num is None:
			for i in range(self.param_count()):
				out += "free(param[{index}].buffer);\n".format(index=i)
		else:
			for i in range(num):
				out += "free(param[{index}].buffer);\n".format(index=i)
		return out

	def col_buffer_free(self, num: int = None):
		"""
		Frees allocated param buffers after use.
		"""
		out = "/* Generated using col_buffer_free() */\n"
		if num is None:
			for i in range(self.col_count()):
				out += "free(param[{index}].buffer);\n".format(index=i)
		else:
			for i in range(num):
				out += "free(param[{index}].buffer);\n".format(index=i)
		return out

	def get_update_fk(self):
		"""
		Updates Foreign Key references in 'UPDATE' or 'INSERT' methods.
		"""
		out = "/* Generated using get_update_fk() */\n"
		for prop in self.members:
			if prop.proptype == SqlType.FK_LONG:
				out += """
				if ({name}T->{fk_name} == NULL){{
					fprintf(stderr, "%s->%s is NULL\\n", "{name}", "{fk_name}");
					return 0U;
				}} else if ({name}T->{fk_name}->{fk_id} == 0) {{
					{fk_name}_insert(conn, {name}T->{fk_name});
				}} else {{
					{fk_name}_update(conn, {name}T->{fk_name});
				}}""".format(name=self.name, fk_name=prop.name.replace("_id", "").replace("id_", ""), fk_id=prop.name)
		return out

	def col_param_lengths(self, func_ref: FunctionTemplate):
		"""
		Defines '#define' preprocessor directives and stack variables specifying sizes of column fields.
		"""
		out = "/* Generated using col_param_lengths() */\n"
		out += "MYSQL_BIND param[PARAM_COUNT];\n"
		out += "memset(&param, 0, sizeof(param));\n"
		for prop in self.members:
			if prop.proptype in [SqlType.VARCHAR, SqlType.TEXT]:
				out += self.col_param_length(prop, func_ref)
		return out

	def col_param_length(self, prop, func_ref: FunctionTemplate):
		"""
		Defines '#define' preprocessor directive and stack variable specifying size of the column field.
		"""
		func_ref.add_macro_def(
			MacroDefinition("{name_upper}_SIZE".format(name_upper=prop.name.upper()), str(prop.size)))
		return """
			unsigned long {name}_len;
			{name}_len = strnlen({struct}T->{name}, {name_upper}_SIZE);
			""".format(name=prop.name, name_upper=prop.name.upper(), struct=self.name)

	def col_update_params(self, func_ref: FunctionTemplate):
		"""
		Allocates and bind param buffers used in execute methods.
		Be sure to free allocated buffers with col_param_buffer_free().
		"""
		out = "/* Generated using col_update_params() */\n"
		out += "MYSQL_BIND param[PARAM_COUNT];\n"
		out += "memset(&param, 0, sizeof(param));\n"
		memb = self.members.copy()
		memb.append(memb.pop(0))
		for prop in memb:
			if prop.proptype in [SqlType.VARCHAR, SqlType.TEXT]:
				out += self.col_param_length(prop, func_ref)

		for i, prop in enumerate(memb):
			out += self.col_param_from_prop(i, prop, self.name)
		return out

	def get_insert_assertions(self):
		"""
		Generates basic assertions for 'INSERT' query.
		"""
		out = "/* Generated using get_insert_assertions() */\n"
		for prop in self.members:
			if prop.proptype == SqlType.PK_LONG:
				if self.get_pk() is not None:
					out += "assert({name}T->{pk_name} == 0);\n".format(name=self.name, pk_name=self.get_pk().name)
			elif prop.proptype == SqlType.VARCHAR:
				out += "assert(strnlen({name}T->{prop_name}, STRING_SIZE) > 1);\n".format(name=self.name,
				                                                                          prop_name=prop.name)
		return out

	def get_free_members(self):
		out = "/* Generated by get_free_members() */\n"
		out += "assert(ptr != NULL);\n"
		out += "\n"
		out += "if (*ptr != NULL){\n"
		for prop in self.members:
			if prop.proptype == SqlType.FK_LONG:
				out += "{name}_free(&((*ptr)->{name}));\n".format(name=prop.name.replace("_id", "").replace("id_", ""))
		out += "free(*ptr);\n"
		out += "}"
		return out

	def get_params(self):
		return [prop for prop in self.members[1:]]

	def get_pk(self):
		for mem in self.members:
			if mem.proptype == SqlType.PK_LONG:
				return mem
		return None
