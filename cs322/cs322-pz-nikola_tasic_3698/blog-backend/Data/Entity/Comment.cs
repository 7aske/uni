using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace blog_backend.Data;

[Table("comment")]
public class Comment : Auditable {
	[Key, Column("comment_id")]
	public int Id { get; set; }

	[Column("user_fk")]
	[ForeignKey("User")]
	public int? UserFk { get; set; }

	[ForeignKey("UserFk")]
	public virtual User? User { get; set; }

	[Column("post_fk")]
	[ForeignKey("Post")]
	public int? PostFk { get; set; }

	[ForeignKey("PostFk")]
	public Post Post { get; set; }

	[Column("body")]
	public string Body { get; set; }

}