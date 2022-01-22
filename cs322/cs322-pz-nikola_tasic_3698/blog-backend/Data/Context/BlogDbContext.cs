using Microsoft.EntityFrameworkCore;

namespace blog_backend.Data.Context;

public class BlogDbContext : DbContext {
	public BlogDbContext(DbContextOptions options) : base(options) {
	}

	public DbSet<Post> Posts { get; set; }
	public DbSet<User> Users { get; set; }
	public DbSet<Category> Categories { get; set; }
	public DbSet<Tag> Tags { get; set; }
	public DbSet<Comment> Comments { get; set; }
	public DbSet<Notification> Notifications { get; set; }

	protected override void OnModelCreating(ModelBuilder modelBuilder) {
		modelBuilder.ApplyConfiguration(new PostModelConfig());
	}
}