using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace blog_backend.Data;

[Table("notification")]
public class Notification : Auditable {
	[Key, Column("notification_id")]
	public int Id { get; set; }

	[Column("user_fk")]
	[ForeignKey("User")]
	public int? UserFk { get; set; }

	[ForeignKey("UserFk")]
	public virtual User? User { get; set; }

	[Column("Title")]
	public string? Title { get; set; }

	[Column("body")]
	public string Body { get; set; }

	[Column("seen")]
	public bool Seen { get; set; }

	[Column("read")]
	public bool Read { get; set; }

	[Column("type")]
	public string? Type { get; set; }

	[Column("action_url")]
	public string? ActionUrl { get; set; }
}