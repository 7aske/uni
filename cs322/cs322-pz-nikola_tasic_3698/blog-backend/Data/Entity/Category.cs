using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace blog_backend.Data;

[Table("category")]
public class Category : Auditable {
	[Key, Column("category_id")]
	public int Id { get; set; }
	[Column("name")]
	public string Name { get; set; }
}