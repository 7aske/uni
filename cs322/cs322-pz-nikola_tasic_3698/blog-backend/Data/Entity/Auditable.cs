using System.ComponentModel;
using System.ComponentModel.DataAnnotations.Schema;

namespace blog_backend.Data;

public abstract class Auditable {
	[Column("created_date")]
	public DateTime CreatedDate { get; set; }
	[Column("last_modified_by"), DefaultValue("system")]
	public string? LastModifiedBy { get; set; }
	[Column("last_modified_date")]
	public DateTime LastModifiedDate { get; set; }
	[Column("record_status")]
	public int RecordStatus { get; set; }
}