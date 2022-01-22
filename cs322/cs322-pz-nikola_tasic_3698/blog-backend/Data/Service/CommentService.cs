using blog_backend.Util;

namespace blog_backend.Data.Repository;

public interface CommentService {
	PaginatedList<Comment> GetAllByPostId(int postId, Pageable? pageable);
	Comment SaveForPost(int postId, Comment comment);
}