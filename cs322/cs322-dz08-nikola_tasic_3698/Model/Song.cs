using System.ComponentModel.DataAnnotations.Schema;

namespace cs322_dz08_nikola_tasic_3698.Model;

[Table("song")]
public class Song {
	[Column("song_id")]
	public int id { set; get; }
	public string artist { set; get; }
	public string name { set; get; }

	public Song() {
	}

	public Song(int id, string artist, string name) {
		this.id = id;
		this.artist = artist;
		this.name = name;
	}

	public override string ToString() {
		return $"{artist} - {name}";
	}
}