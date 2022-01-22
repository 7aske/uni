using blog_backend.Util;

namespace blog_backend.Data.Repository; 

public interface PostService {
	PaginatedList<Post> GetAll(Pageable? pageable);
	Post GetById(int id);
	Post GetBySlug(string slug);
	Post Save(Post post);
	Post Update(Post post);

	void Delete(int postId);
}