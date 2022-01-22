using System.Text;
using blog_backend.Data.Context;
using blog_backend.Data.Repository;
using blog_backend.Data.Repository.Impl;
using blog_backend.Security;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.HttpLogging;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;

WebApplicationBuilder builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddDbContext<BlogDbContext>(opt => {
	string connectionString =
		builder.Configuration.GetConnectionString("Default");
	opt.UseMySQL(connectionString);
});
builder.Services.AddScoped<AuditableContext, AuditableContext>();
builder.Services.AddScoped<PostService, PostServiceImpl>();
builder.Services.AddScoped<UserService, UserServiceImpl>();
builder.Services.AddScoped<CategoryService, CategoryServiceImpl>();
builder.Services.AddScoped<TagService, TagServiceImpl>();
builder.Services.AddScoped<CommentService, CommentServiceImpl>();
builder.Services.AddScoped<NotificationService, NotificationServiceImpl>();
builder.Services.AddControllers();
// here we add MVC but disable model validation
builder.Services.AddMvc(options => options.ModelValidatorProviders.Clear());

var jwtTokenConfig = builder.Configuration.GetSection("JwtToken").Get<JwtTokenConfig>();
builder.Services.AddSingleton(jwtTokenConfig);
builder.Services.AddScoped<JwtAuthManager, JwtAuthManager>();
builder.Services.AddHttpContextAccessor();
builder.Services.AddAuthentication(options => {
	options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
	options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
}).AddJwtBearer(options => {
	options.SaveToken = true;
	options.TokenValidationParameters = new TokenValidationParameters
	{
		ValidateIssuer = true,
		ValidIssuer = jwtTokenConfig.Issuer,
		ValidateIssuerSigningKey = true,
		IssuerSigningKey = new SymmetricSecurityKey(Encoding.ASCII.GetBytes(jwtTokenConfig.Secret)),
		ValidAudience = jwtTokenConfig.Audience,
		ValidateAudience = true,
		ValidateLifetime = true,
		ClockSkew = TimeSpan.FromMinutes(1)
	};
});

// CORS configuration
builder.Services.AddCors(options => {
	options.AddDefaultPolicy(corsPolicyBuilder =>
		corsPolicyBuilder.WithOrigins("*")
			// allowing the header that shows total number of items in the api call
			.AllowAnyMethod()
			.WithExposedHeaders("X-Data-Count")
			.AllowAnyHeader()
	);
});

// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddHttpLogging(options => {
	options.LoggingFields = HttpLoggingFields.RequestPropertiesAndHeaders |
	                        HttpLoggingFields.ResponsePropertiesAndHeaders;
});

WebApplication app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment()) {
	app.UseSwagger();
	app.UseSwaggerUI();
	app.UseHttpLogging();
}

// app.UseHttpsRedirection();
app.UseAuthentication();
app.UseAuthorization();
app.UseCors();
app.MapControllers();

app.Run();