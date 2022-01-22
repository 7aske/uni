using System;
using System.Collections.Generic;
using System.Linq;
using MySql.Data.MySqlClient;
using Org.BouncyCastle.Cms;

namespace cs322_dz07_nikola_tasic_3698 {
	internal class Program {
		private const string createTable = @"
create table if not exists `song` (
    `song_id` int primary key auto_increment,
    `artist` varchar(64) not null,
    `name` varchar(64) not null
);
";
		
		/**
		 * NOTE:
		 * Profesore, nisam koristio Windows Forms jer sam obrisao Windows.
		 * Mislim da je ova implementacija i više nego dovoljna.
		 */
		// ReSharper disable once InconsistentNaming
		public static void Main(string[] args) {
			string cs = @"server=localhost;userid=cs322;password=cs322;database=cs322";

			MySqlConnection con = new MySqlConnection(cs);
			con.Open();

			setupTables(con);

			Song enterSandman = addSong(new Song(0, "Metallica", "Enter Sandman"), con);
			Song forWhomTheBellTolls = addSong(new Song(0, "Metallica", "For who the Bell Tolls"), con);
			Song ironMan = addSong(new Song(0, "Metallica", "Iron Man"), con);
			ironMan.artist = "Black Sabbath";
			forWhomTheBellTolls.name = "For Whom the Bell Tolls";
			updateSong(ironMan, con);
			updateSong(forWhomTheBellTolls, con);
			deleteSong(forWhomTheBellTolls, con);

			List<Song> songs = getAllSongs(con);
			foreach (Song song in songs) {
				Console.WriteLine(song);
			}


			con.Close();
		}

		private static void setupTables(MySqlConnection con) {
			MySqlCommand createCommand = new MySqlCommand(createTable, con);
			createCommand.ExecuteScalar();
		}

		private static List<Song> getAllSongs(MySqlConnection connection) {
			MySqlCommand select = new MySqlCommand("select song_id, artist, name from song;", connection);

			MySqlDataReader rdr = select.ExecuteReader();
			List<Song> allSongs = new List<Song>();

			while (rdr.Read()) {
				int id = rdr.GetInt32(0);
				string artist = rdr.GetString(1);
				string name = rdr.GetString(2);
				allSongs.Add(new Song(id, artist, name));
			}

			return allSongs;
		}

		private static Song addSong(Song song, MySqlConnection connection) {
			MySqlCommand insert = new MySqlCommand("insert into song(artist, name) values (@artist, @name);", connection);
			
			insert.Parameters.AddWithValue("@artist", song.artist);
			insert.Parameters.AddWithValue("@name", song.name);
			insert.Prepare();

			insert.ExecuteNonQuery();
			song.id = (int) insert.LastInsertedId;

			return song;
		}
		
		private static Song updateSong(Song song, MySqlConnection connection) {
			MySqlCommand update = new MySqlCommand("update song set name = @name, artist = @artist where song_id = @id", connection);
			
			update.Parameters.AddWithValue("@id", song.id);
			update.Parameters.AddWithValue("@artist", song.artist);
			update.Parameters.AddWithValue("@name", song.name);
			update.Prepare();
			
			update.ExecuteNonQuery();

			return song;
		}
		
		private static void deleteSong(Song song, MySqlConnection connection) {
			MySqlCommand delete = new MySqlCommand("delete from song where song_id = @id", connection);
			
			delete.Parameters.AddWithValue("@id", song.id);
			delete.Prepare();
			
			delete.ExecuteNonQuery();
		}
	}
}