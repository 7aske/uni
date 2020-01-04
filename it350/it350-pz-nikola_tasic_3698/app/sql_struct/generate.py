from shutil import copy2
from subprocess import Popen
from os.path import exists
from typing import List
from datetime import datetime as dt

import pprint
import os
import json

from function import FunctionTemplate, FunctionArgument, MacroDefinition
from header import Header
from struct import Struct
from sql_types import SqlType, SqlColumn

proj_name = ""


def main():
	global proj_name
	out_dir = ".."
	# out_dir = "dist"

	inp_file = "ddl/library.sql"

	assert exists(inp_file)

	name = os.path.splitext(os.path.basename(inp_file))[0]
	proj_name = name
	node = Popen(["node", "index.js", inp_file], stdout=None)
	node.wait()

	assert node.returncode == 0

	with open("ddl/" + name + ".json", "r") as file:
		json_string = file.read()

	json_data = json.loads(json_string)
	structs = []
	pprint.pprint(json_data)
	pprint.pprint(len(json_data))
	for table in json_data:
		struct_name = table["name"]
		structs_members = []
		for column in table["columns"]:
			col_name = column["name"]
			col_type = column["type"]["datatype"]
			col_type_sql = None

			if "length" in column["type"].keys():
				col_size = column["type"]["length"]
			elif "width" in column["type"].keys():
				col_size = column["type"]["width"]
			else:
				if "fractional" in column["type"].keys() and col_type in ["date", "time"]:
					col_size = 56
				else:
					msg = f"SQL type size not handled '{col_name}' in struct '{struct_name}'"
					assert False, msg

			if {"column": col_name} in table["primaryKey"]["columns"]:
				col_type_sql = SqlType.PK_LONG
			elif col_type == "varchar":
				col_type_sql = SqlType.VARCHAR
			elif col_type == "char":
				col_type_sql = SqlType.VARCHAR
			elif col_type == "text":
				col_type_sql = SqlType.TEXT
			elif col_type == "int":
				col_type_sql = SqlType.LONG
			elif col_type == "date":
				col_type_sql = SqlType.DATE
			else:
				msg = f"SQL type not handled '{col_type}' in struct '{struct_name}'"
				assert False, msg

			if "foreignKeys" in table.keys():
				if {"column": col_name} in [fk["columns"][0] for fk in table["foreignKeys"]]:
					col_type_sql = SqlType.FK_LONG
			elif "indexes" in table.keys():
				if {"column": col_name} in [fk["columns"][0] for fk in table["indexes"]]:
					col_type_sql = SqlType.FK_LONG

			# assert col_type_sql is not None, err_msg
			if col_type_sql is not None:
				structs_members.append(SqlColumn(col_name, col_type_sql, col_size))
		print(struct_name)
		[print(mem) for mem in structs_members]
		structs.append(Struct(struct_name, structs_members))

	for struct in structs:
		save_to_file(struct, out_dir)

	with open(out_dir + "/internal/db/orm/entity.h", "w") as file:
		file.write(generate_entity_h(structs))

	with open(out_dir + "/internal/db/sql_result.h", "w") as file:
		file.write(generate_sql_result_h(structs))
	for root, dirs, files in os.walk('common', topdown=True):
		path = root.split(os.sep)
		for file in files:
			pth = "/".join(path[1:]) + "/" + file
			if file == "dbc.c":
				with open("common" + "/" + pth, "r") as dbc:
					with open(out_dir + "/" + pth, "w") as dbcd:
						dbcd.write(dbc.read() + generate_dbc_c(structs))
			else:
				copy2("common" + "/" + pth, out_dir + "/" + pth)


def generate_sql_result_h(structs: List[Struct]):
	out = """
#ifndef __{uname}_{proj}_APP_SQL_RESULT_H
#define __{uname}_{proj}_APP_SQL_RESULT_H

typedef unsigned int uint;

enum entity_type {{
{entities}}};

struct sql_result_row {{
	void* data;
	struct sql_result_row* next;
}};

struct sql_result {{
	struct sql_result_row* results;
	uint count;
	enum entity_type type;
}};

typedef enum entity_type ENTITY_TYPE;
typedef struct sql_result SQL_RESULT;
typedef struct sql_result_row SQL_RESULT_ROW;

#endif //{uname}_APP_SQL_RESULT_H
""".format(proj=proj_name.upper(), uname=os.getlogin(),
	       entities="".join(["\t" + struct.name.upper() + "_E,\n" for struct in structs]))
	return out


def generate_dbc_c(structs: List[Struct]):
	switch = ""
	for struct in structs:
		switch += """
			case {enum}:
			free_func = (void (*)(void**)) {name}_free;
			break;""".format(enum=struct.enum_name, name=struct.name.replace("_id", "").replace("id_", ""))
	out = """
void mysql_res_free(SQL_RESULT** res) {{
	assert(res != NULL);
	SQL_RESULT_ROW* curr;
	SQL_RESULT_ROW* prev;
	void (* free_func)(void**) = NULL;

	if (*res != NULL) {{
		switch((*res)->type){{
		{switch}
		}}
		curr = (*res)->results;
		while (curr != NULL) {{
			assert(free_func != NULL);
			
			prev = curr;
			curr = curr->next;
			free_func(&prev->data);
			free(prev);
		}}
		free(*res);
		*res = NULL;
	}}

}} 
	""".format(switch=switch)

	return out


def generate_entity_h(structs: List[Struct]):
	entity_h = Header()
	entity_h.set_header_guard("__{name}_{proj}_DB_ENTITY_H".format(name=os.getlogin().upper(), proj=proj_name.upper()))
	entity_h.add_pragma("once")
	entity_h.add_global_include("string.h")
	entity_h.add_global_include("stdlib.h")
	entity_h.add_global_include("mysql/mysql.h")

	entity_h.add_local_include("db/dbc.h")

	for struct in structs:
		entity_h.add_local_include(f"db/orm/{struct.name}.h")

	return str(entity_h)


def save_to_file(struct: Struct, out_dir: str = "out"):
	"""
	Saves all structs methods to file '{name}.c'.
	"""
	insert_func = declaration_insert(struct)
	struct.methods.append(insert_func)
	execute_find_func = declaration_execute_find(struct)
	struct.methods.append(execute_find_func)
	find_by_id_func = declaration_find_by_id(struct)
	struct.methods.append(find_by_id_func)
	update_func = declaration_update(struct)
	struct.methods.append(update_func)
	execute_func = declaration_execute(struct)
	struct.methods.append(execute_func)
	delete_func = declaration_delete(struct)
	struct.methods.append(delete_func)

	find_all_func = declaration_find_all(struct)
	struct.methods.append(find_all_func)

	free_func = declaration_free(struct)
	struct.methods.append(free_func)

	# add bodies to functions
	if struct.get_pk() is not None:
		body_find_by_id(struct, find_by_id_func)
	body_execute_find(struct, execute_find_func)
	body_insert(struct, insert_func)
	body_update(struct, update_func)
	body_execute(struct, execute_func)
	body_delete(struct, delete_func)

	body_find_all(struct, find_all_func)

	body_free(struct, free_func)

	file_header = ("/**\n"
	               " * Generated by {user} on {time}\n"
	               " */\n"
	               ).format(user=os.getlogin(), time=dt.now().strftime("%d/%m/%Y")).lstrip("\t").rstrip("\t")
	if not exists(out_dir):
		os.mkdir(out_dir)
	if not exists(out_dir + "/src"):
		os.mkdir(out_dir + "/src")
	if not exists(out_dir + "/src/db"):
		os.mkdir(out_dir + "/src/db")
	if not exists(out_dir + "/src/db/orm"):
		os.mkdir(out_dir + "/src/db/orm")
	with open(out_dir + "/src/db/orm/{name}.c".format(name=struct.name), "w") as file:
		file.write(file_header)

		file.write(f"\n#include \"db/orm/{struct.name}.h\"\n")

		for func in struct.methods:
			file.write(str(func))

	if not exists(out_dir + "/internal"):
		os.mkdir(out_dir + "/internal")
	if not exists(out_dir + "/internal/db"):
		os.mkdir(out_dir + "/internal/db")
	if not exists(out_dir + "/internal/db/orm"):
		os.mkdir(out_dir + "/internal/db/orm")
	with open(out_dir + "/internal/db/orm/{name}.h".format(name=struct.name), "w") as file:
		file.write(file_header)
		file.write(generate_h(struct))


def generate_h(struct: Struct):
	header = Header()
	header.set_header_guard(
		"__{uname}_{proj}_DB_ENTITY_STRUCT_{name}_H".format(name=struct.name.upper(), proj=proj_name.upper(),
		                                                    uname=os.getlogin()))
	header.add_pragma("once")
	header.add_local_include("db/orm/entity.h")
	header.add_struct(struct)
	return str(header)


def insert_sql_string(struct: Struct):
	"""
	Generates 'INSERT' SQL command for respective struct.
	"""
	return f'insert into {struct.name} ({", ".join([param.name for param in struct.get_params()])}) values ({", ".join(("?" * struct.param_count()))});'


def update_sql_string(struct: Struct):
	"""
	Generates 'UPDATE' SQL command for respective struct.
	"""
	# noinspection SqlResolve
	return f'update {struct.name} set {", ".join([param.name + " = ?" for param in struct.get_params()])} where id_{struct.name} = ?;'


def find_by_id_sql_string(struct: Struct):
	"""
	Generates 'SELECT' SQL command for respective struct that finds by ID.
	"""
	# noinspection SqlResolve
	return f'select * from {struct.name} where id_{struct.name} = ?;'


def find_all_sql_string(struct: Struct):
	"""
	Generates 'SELECT' SQL command for respective struct that finds all rows.
	"""
	# noinspection SqlResolve
	return f'select * from {struct.name};'


def delete_sql_string(struct: Struct):
	"""
	Generates 'DELETE' SQL command for respective struct that deletes by ID.
	"""
	# noinspection SqlResolve
	return f'delete from {struct.name} where id_{struct.name} = ?;'


def declaration_execute_find(struct: Struct):
	arg_list = [FunctionArgument("MYSQL*", "conn"),
	            FunctionArgument("char const*", "query"),
	            FunctionArgument("MYSQL_BIND*", "params"),
	            FunctionArgument("uint", "param_count")]
	func = FunctionTemplate("{name}_execute_find".format(name=struct.name), "SQL_RESULT*", arg_list)
	return func


def body_execute_find(struct: Struct, func: FunctionTemplate):
	func.add_macro_def(MacroDefinition("QUERY_SIZE", "512"))
	func.add_macro_def(MacroDefinition("RES_COL_COUNT", str(struct.col_count())))
	func.add_macro_def(MacroDefinition("BUFFER_SIZE", "255"))
	func.add_block("""
		SQL_RESULT* res;
		MYSQL_RES* prepare_meta_result;
		MYSQL_STMT* stmt;""")
	func.add_block('\tif (conn == NULL) {\n\t\tconn = db_init();\n\t}\n')
	func.add_block(struct.get_col_buffer_definitions())
	func.add_block("stmt = mysql_stmt_init(conn);")
	func.add_block("""if (mysql_stmt_prepare(stmt, query, strnlen(query, QUERY_SIZE))) {
				fprintf(stderr, " mysql_stmt_prepare(), SELECT failed\\n");
				fprintf(stderr, " %s\\n", mysql_stmt_error(stmt));
				return NULL;
			}
			mysql_stmt_bind_param(stmt, params);
			assert(param_count == mysql_stmt_param_count(stmt));""")
	func.add_block("""/* Fetch result set meta information */
			prepare_meta_result = mysql_stmt_result_metadata(stmt);
			if (!prepare_meta_result) {
				fprintf(stderr, " mysql_stmt_result_metadata(), returned no meta information\\n");
				fprintf(stderr, " %s\\n", mysql_stmt_error(stmt));
				return NULL;
			}
			assert(RES_COL_COUNT == mysql_num_fields(prepare_meta_result));""")
	func.add_block("""/* Execute the SELECT query */
		if (mysql_stmt_execute(stmt)) {
			fprintf(stderr, " mysql_stmt_execute(), failed\\n");
			fprintf(stderr, " %s\\n", mysql_stmt_error(stmt));
			return NULL;
		}""")

	func.add_block(struct.get_buffer_bindings())
	func.add_block("""/* Bind the result buffers */
		if (mysql_stmt_bind_result(stmt, param)) {
			fprintf(stderr, " mysql_stmt_bind_result() failed\\n");
			fprintf(stderr, " %s\\n", mysql_stmt_error(stmt));
			return NULL;
		}""")
	func.add_block("""/* Now buffer all results to client (optional step) */
		if (mysql_stmt_store_result(stmt)) {
			fprintf(stderr, " mysql_stmt_store_result() failed\\n");
			fprintf(stderr, " %s\\n", mysql_stmt_error(stmt));
			return NULL;
		}""")
	func.add_block("""/* Fetch all rows */
			struct sql_result_row* row = NULL;
			struct sql_result_row* curr = NULL;
		
			res = calloc(1, sizeof(SQL_RESULT));
			res->results = NULL;
			res->type = {enum_name};
			res->count = 0;""".format(enum_name=struct.enum_name))
	func.add_block(struct.col_fetch())
	# TODO probably should remove error checking here v
	func.add_block("""/* Free the prepared result metadata */
			mysql_free_result(prepare_meta_result);
			if (mysql_stmt_close(stmt)) {
				fprintf(stderr, " failed while closing the statement\\n");
				fprintf(stderr, " %s\\n", mysql_error(conn));
				mysql_res_free(&res);
				return NULL;
			}""")
	func.add_block("return res;")


def declaration_find_by_id(struct: Struct):
	arg_list = [FunctionArgument("MYSQL*", "conn"),
	            FunctionArgument("uint", "id")]
	return FunctionTemplate("{}_find_by_id".format(struct.name), "{}*".format(struct.typedef_name), arg_list)


def body_find_by_id(struct: Struct, func: FunctionTemplate):
	func.add_macro_def(MacroDefinition("QUERY", '"{}"'.format(find_by_id_sql_string(struct))))
	func.add_macro_def(MacroDefinition("PARAM_COUNT", "1"))
	func.add_block('\tif (conn == NULL) {\n\t\tconn = db_init();\n\t}\n')
	func.add_block("{}* out;".format(struct.typedef_name))
	func.add_block("""
			SQL_RESULT* res;
			struct {name} {name};
			{name}.{pk_name} = id;
			struct {name}* {name}T = &{name};
			""".format(name=struct.name, pk_name=struct.get_pk().name))
	func.add_block(struct.get_col_param_buffer(["id_{}".format(struct.name)]))
	func.add_block("res = {}_execute_find(conn, QUERY, param, PARAM_COUNT);".format(struct.name))
	func.add_block(struct.col_param_buffer_free(1))
	func.add_block("\tif (res->results == NULL) { return NULL; }")
	func.add_block("""out = res->results->data;
			if (res->count == 1) {{
				free(res->results);
				free(res);
				return out;
			}} else {{
				fprintf(stderr, "{name}_execute_find(), failed - multiple results (%d)\\n", res->count);
				mysql_res_free(&res);
				return NULL;
			}}""".format(name=struct.name))
	return func


def declaration_find_all(struct: Struct):
	args = [FunctionArgument("MYSQL*", "conn")]
	return FunctionTemplate("{}_find_all".format(struct.name), "SQL_RESULT*".format(struct.typedef_name), args)


def body_find_all(struct: Struct, func: FunctionTemplate):
	func.add_macro_def(MacroDefinition("QUERY", '"{}"'.format(find_all_sql_string(struct))))
	func.add_macro_def(MacroDefinition("PARAM_COUNT", str(0)))
	func.add_block("SQL_RESULT* res;")
	func.add_block("MYSQL_BIND param[1];")
	func.add_block('\tif (conn == NULL) {\n\t\tconn = db_init();\n\t}\n')
	func.add_block("res = {}_execute_find(conn, QUERY, param, PARAM_COUNT);".format(struct.name))
	func.add_block("return res;")
	return func


def declaration_insert(struct: Struct):
	arg_list = [FunctionArgument("MYSQL*", "conn"),
	            FunctionArgument("{}*".format(struct.typedef_name), "{}T".format(struct.name))]
	return FunctionTemplate("{}_insert".format(struct.name), "uint", arg_list)


def body_insert(struct: Struct, func: FunctionTemplate):
	func.add_macro_def(MacroDefinition("QUERY_LENGTH", "512"))
	func.add_macro_def(MacroDefinition("STRING_SIZE", "255"))
	func.add_macro_def(MacroDefinition("QUERY", '"{}"'.format(insert_sql_string(struct))))
	func.add_macro_def(MacroDefinition("PARAM_COUNT", "{}".format(struct.param_count())))
	func.add_block(struct.get_insert_assertions())
	func.add_block("""
				MYSQL_STMT* __attribute__((cleanup(mysql_stmt_cleanup))) stmt;
				uint retval;
				""")
	func.add_block('\tif (conn == NULL) {\n\t\tconn = db_init();\n\t}\n')
	func.add_block('\tstmt = mysql_stmt_init(conn);\n')
	func.add_block(struct.get_update_fk())
	func.add_block(struct.col_param_lengths(func))
	func.add_block(struct.get_col_param_buffers())
	func.add_block("""if (mysql_stmt_prepare(stmt, QUERY, QUERY_LENGTH)) {
					fprintf(stderr, " mysql_stmt_prepare(), failed\\n");
					fprintf(stderr, " %s\\n", mysql_stmt_error(stmt));
					return 0U;
					}""")
	func.add_block("""if (mysql_stmt_bind_param(stmt, param)) {
					fprintf(stderr, " mysql_stmt_bind_param(), failed\\n");
					fprintf(stderr, " %s\\n", mysql_stmt_error(stmt));
					return 0U;
				}""")
	func.add_block("""if (mysql_stmt_execute(stmt)) {
					fprintf(stderr, " mysql_stmt_execute(), failed\\n");
					fprintf(stderr, " %s\\n", mysql_stmt_error(stmt));
					return 0U;
				}""")
	func.add_block("retval = (uint) mysql_stmt_insert_id(stmt);")
	if struct.get_pk() is not None:
		func.add_block(("// update id after insertion;\n"
		                "{name}T->{pk} = retval;\n").format(name=struct.name, pk=struct.get_pk().name))
	func.add_block(struct.col_param_buffer_free())
	func.add_block("return retval;")
	return func


def declaration_update(struct: Struct):
	arg_list = [FunctionArgument("MYSQL*", "conn"),
	            FunctionArgument("{}*".format(struct.typedef_name), "{}T".format(struct.name))]
	return FunctionTemplate("{}_update".format(struct.name), "int", arg_list)


def body_update(struct: Struct, func: FunctionTemplate):
	if struct.get_pk() is not None:
		func.add_block("assert({name}T->{pk_name} != 0);".format(name=struct.name, pk_name=struct.get_pk().name))
	func.add_macro_def(MacroDefinition("QUERY", '"{}"'.format(update_sql_string(struct))))
	func.add_macro_def(MacroDefinition("PARAM_COUNT", str(struct.col_count())))
	func.add_macro_def(MacroDefinition("STRING_SIZE", "255"))
	func.add_block('\tif (conn == NULL) {\n\t\tconn = db_init();\n\t}\n')
	func.add_block("""int retval;""")
	func.add_block(struct.col_update_params(func))
	func.add_block("retval = {}_execute(conn, QUERY, param, PARAM_COUNT);".format(struct.name))
	func.add_block(struct.col_buffer_free())
	func.add_block("return retval;")
	return func


def declaration_delete(struct: Struct):
	arg_list = [FunctionArgument("MYSQL*", "conn"),
	            FunctionArgument("{}*".format(struct.typedef_name), "{}T".format(struct.name))]
	return FunctionTemplate("{}_delete".format(struct.name), "int", arg_list)


def body_delete(struct: Struct, func: FunctionTemplate):
	func.add_macro_def(MacroDefinition("QUERY", '"{}"'.format(delete_sql_string(struct))))
	func.add_macro_def(MacroDefinition("PARAM_COUNT", str(1)))
	if struct.get_pk() is not None:
		func.add_block("assert({name}T->{pk_name} != 0);".format(name=struct.name, pk_name=struct.get_pk().name))
	func.add_block('\tif (conn == NULL) {\n\t\tconn = db_init();\n\t}\n')
	func.add_block("""int retval;""")
	func.add_block(struct.get_col_param_buffer(["id_{}".format(struct.name)]))
	func.add_block("retval = {}_execute(conn, QUERY, param, PARAM_COUNT);".format(struct.name))
	func.add_block(struct.col_param_buffer_free(1))

	func.add_block("return retval;")
	return func


def declaration_execute(struct: Struct):
	arg_list = [FunctionArgument("MYSQL*", "conn"),
	            FunctionArgument("char const*", "query"),
	            FunctionArgument("MYSQL_BIND*", "params"),
	            FunctionArgument("uint", "param_count")]
	return FunctionTemplate("{name}_execute".format(name=struct.name), "int", arg_list)


def body_execute(struct: Struct, func: FunctionTemplate):
	# func = struct.declaration_execute()
	func.add_block("/* Generated by body_execute */")
	func.add_macro_def(MacroDefinition("QUERY_LENGTH", '512'))
	func.add_block('\tMYSQL_STMT* stmt;\t\nint retval;')
	func.add_block('\tif (conn == NULL) {\n\t\tconn = db_init();\n\t}\n')
	func.add_block('\t\nstmt = mysql_stmt_init(conn);')
	func.add_block("""if ((retval = mysql_stmt_prepare(stmt, query, strnlen(query, QUERY_LENGTH)))) {
					fprintf(stderr, "mysql_stmt_prepare(), failed\\n");
					fprintf(stderr, "%s\\n", mysql_error(conn));
					return retval;
				}""")
	func.add_block("""if ((retval = mysql_stmt_bind_param(stmt, params))) {
					fprintf(stderr, "mysql_stmt_bind_param(), failed\\n");
					fprintf(stderr, "%s\\n", mysql_error(conn));
					return retval;
				}""")
	func.add_block("""if ((retval = mysql_stmt_execute(stmt))) {
					fprintf(stderr, "mysql_stmt_execute(), failed\\n");
					fprintf(stderr, "%s\\n", mysql_error(conn));
					return retval;
				}""")
	func.add_block("""if ((retval = mysql_stmt_close(stmt))) {
					fprintf(stderr, " failed while closing the statement\\n");
					fprintf(stderr, " %s\\n", mysql_error(conn));
					return retval;
				}""")
	return func


def declaration_free(struct: Struct):
	arg_list = [FunctionArgument("{}**".format(struct.typedef_name), "ptr")]
	return FunctionTemplate("{name}_free".format(name=struct.name), "void", arg_list)


def body_free(struct: Struct, func: FunctionTemplate):
	func.add_block(struct.get_free_members())
	return str(func)


if __name__ == "__main__":
	main()
