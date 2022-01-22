using MySql.Data.MySqlClient;

void main1() {
	string connectionString = @"server=localhost;userid=cs322;password=cs322;database=cs322";

	var con = new MySqlConnection(connectionString);
	try {
		con.Open();
		var sql = "INSERT INTO Korisnik(username, password) VALUES('test', 'test')";
		using var cmd = new MySqlCommand(sql, con);
		cmd.ExecuteNonQuery();
	} finally {
		con.Close();
	}
}

// nemam windows forme na linux-u sam
void main2() {
	StreamReader? streamReader = null;
	StreamWriter? streamWriter = null;
	try {
		 streamReader = new StreamReader(new FileStream("../../../file.txt", FileMode.Open));
		 string data = streamReader.ReadToEnd();
		 Console.Write(data);
		 streamWriter = new StreamWriter(new FileStream("../../../file2.txt", FileMode.CreateNew));
		 streamWriter.Write(data);
		 streamWriter.Flush();
	} finally {
		streamReader?.Close();
		streamWriter?.Close();
	}
}

// main1();
main2();
