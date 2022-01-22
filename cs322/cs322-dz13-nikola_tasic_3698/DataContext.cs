using cs322_dz13_nikola_tasic_3698.Model;
using Microsoft.EntityFrameworkCore;

namespace cs322_dz13_nikola_tasic_3698;

public class DataContext : DbContext {
	public DbSet<Song> Songs { get; set; }

	protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) {
		IConfigurationRoot configuration = new ConfigurationBuilder()
			.SetBasePath(AppDomain.CurrentDomain.BaseDirectory)
			.AddJsonFile("appsettings.json")
			.Build();
		string connectionString = configuration.GetConnectionString("SongDbContext");
		optionsBuilder.UseMySql(connectionString, ServerVersion.AutoDetect(connectionString));
	}
}