using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text.Json.Serialization;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace blog_backend.Data;

[Table("post")]
public class Post : Auditable {
	[Key, Column("post_id")]
	public int Id { get; set; }

	[Column("title")]
	public string Title { get; set; }

	[Column("body")]
	public string Body { get; set; }

	[Column("excerpt")]
	public string Excerpt { get; set; }

	[Column("slug")]
	public string Slug { get; set; }

	[Column("image")]
	public string? Image { get; set; }

	[Column("views")]
	public int? Views { get; set; }

	[Column("category_fk")]
	[ForeignKey("Category")]
	public int CategoryFk { get; set; }
	
	[ForeignKey("CategoryFk")]
	public virtual Category Category { get; set; }

	[Column("published")]
	public bool Published { get; set; }

	[Column("date_posted")]
	public DateTime? DatePosted { get; set; }
	
	[Column("user_fk")]
	[ForeignKey("User")]
	public int UserFk { get; set; }

	[ForeignKey("UserFk")]
	public virtual User User { get; set; }
}

public class PostModelConfig : IEntityTypeConfiguration<Post> {
	public void Configure(EntityTypeBuilder<Post> builder) {
		builder.HasOne(post => post.User);
	}
}