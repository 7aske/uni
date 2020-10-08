import enum


class SqlType(enum.Enum):
	LONG = 1,
	VARCHAR = 2,
	FK_LONG = 3,
	PK_LONG = 4,
	DATE = 5,
	TEXT = 6,

	def tostring(self):
		if self == SqlType.PK_LONG:
			return "MYSQL_TYPE_LONG"
		if self == SqlType.FK_LONG:
			return "MYSQL_TYPE_LONG"
		if self == SqlType.LONG:
			return "MYSQL_TYPE_LONG"
		if self == SqlType.VARCHAR:
			return "MYSQL_TYPE_STRING"
		if self == SqlType.DATE:
			return "MYSQL_TYPE_DATE"
		if self == SqlType.TEXT:
			return "MYSQL_TYPE_STRING"


class SqlColumn:
	def __init__(self, name: str, proptype: SqlType, size: int):
		self.name = name
		self.proptype = proptype
		self.size = size
		if proptype in [SqlType.VARCHAR, SqlType.TEXT]:
			self.size += 1  # adjusting for the '\0' character needed in C strings

	def __repr__(self):
		return "<PropType name: {:10} proptype: {:16} size: {:4}>".format(self.name, self.proptype, self.size)
