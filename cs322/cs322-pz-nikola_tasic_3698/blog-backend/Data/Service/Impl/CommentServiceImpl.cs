using blog_backend.Data.Context;
using blog_backend.Util;
using Microsoft.EntityFrameworkCore;

namespace blog_backend.Data.Repository.Impl;

public class CommentServiceImpl : CommentService {
	private readonly AuditableContext context;


	public CommentServiceImpl (AuditableContext context) {
		this.context = context;
	}

	public PaginatedList<Comment> GetAllByPostId(int postId, Pageable? pageable) {
		var source = context
			.Comments
			.Include("User")
			.Include("Post")
			.Where(comment => comment.PostFk == postId);
		
		return PaginatedList<Comment>.Create(source, pageable);
	}

	public Comment SaveForPost(int postId, Comment comment) {
		comment.PostFk = postId;
		comment.Post = null!;
		comment.UserFk = comment.User?.Id;
		comment.User = null!;
		
		var saved = context.Add(comment);
		context.SaveChanges();
		saved.Entity.User =
			context.Users.Single(user => user.Id == comment.UserFk);
		return saved.Entity;
	}
}