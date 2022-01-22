using blog_backend.Data.Context;
using blog_backend.Util;
using Microsoft.EntityFrameworkCore;

namespace blog_backend.Data.Repository.Impl;

public class UserServiceImpl : UserService {
	private readonly AuditableContext context;


	public UserServiceImpl(AuditableContext context) {
		this.context = context;
	}

	public User GetById(int id) {
		return context.Users.Single(e => e.Id == id);
	}
	
	public User GetByUsername(string username) {
		return context.Users.Single(e => e.Username == username);
	}
	
	public PaginatedList<User> GetAll(Pageable? pageable) {
		return PaginatedList<User>.Create(context.Users, pageable);
	}
	
	public bool IsPasswordValid(string plain, string hashed) {
		return BCrypt.Net.BCrypt.Verify(plain, hashed);
	}
}