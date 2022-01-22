using blog_backend.Data.Context;
using blog_backend.Util;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace blog_backend.Data.Repository.Impl;

public class PostServiceImpl : PostService {
	private readonly AuditableContext context;


	public PostServiceImpl(AuditableContext context) {
		this.context = context;
	}

	public PaginatedList<Post> GetAll(Pageable? pageable) {
		return PaginatedList<Post>.Create(context.Posts
			.Include("User")
			.Include("Category"), pageable);
	}

	public Post GetById(int id) {
		return context.Posts.Include("User").Single(e => e.Id == id);
	}

	public Post GetBySlug(string slug) {
		return context.Posts.Include("User").Single(e => e.Slug == slug);
	}

	public Post Save(Post post) {
		// Weird lame hack
		post.CategoryFk = post.Category.Id;
		post.Category = null!;
		post.UserFk = post.User.Id;
		post.User = null!;
		
		var saved = context.Add(post);
		context.SaveChanges();
		return saved.Entity;
	}

	public Post Update(Post post) {
		Post? existing = context.Posts.Find(post.Id);
		if (existing == null) {
			throw new Exception();
		}
		
		EntityEntry<Post> entry = context.Entry(existing);
		entry.CurrentValues.SetValues(post);
		context.SaveChanges();
		return entry.Entity;
	}

	public void Delete(int postId) {
		Post? post = context.Posts.Find(postId);
		if (post == null)
			return;
		context.Posts.Remove(post);
		context.SaveChanges();
	}
}