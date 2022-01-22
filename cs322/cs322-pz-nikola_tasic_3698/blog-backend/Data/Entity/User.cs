using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;

namespace blog_backend.Data;

[Table("user")]
public class User : Auditable {
	[Key, Column("user_id")]
	public int Id { get; set; }

	// @JoinColumn(name = "image_fk", referencedColumnName = "media_id")
	// @ManyToOne
	// private Media image;
	[Column("username")]
	public string Username { get; set; }

	[JsonIgnore]
	[Column("password")]
	public string? Password { get; set; }

	[Column("email")]
	public string Email { get; set; }

	[Column("first_name")]
	public string FirstName { get; set; }

	[Column("last_name")]
	public string LastName { get; set; }

	[Column("about")]
	public String? About { get; set; }

	[Column("display_name")]
	public string DisplayName { get; set; }
}