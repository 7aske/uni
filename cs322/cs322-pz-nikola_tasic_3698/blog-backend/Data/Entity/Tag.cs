using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace blog_backend.Data;

[Table("tag")]
public class Tag : Auditable {
	[Key, Column("tag_id")]
	public int Id { get; set; }
	[Column("name")]
	public string Name { get; set; }
}