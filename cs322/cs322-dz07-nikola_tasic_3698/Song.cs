using System;

namespace cs322_dz07_nikola_tasic_3698 {
	public class Song {
		public int id { set; get; }
		public string artist { set; get; }
		public string name { set; get; }

		public Song(int id, string artist, string name) {
			this.id = id;
			this.artist = artist;
			this.name = name;
		}

		public override string ToString() {
			return $"{artist} - {name}";
		}
	}
}