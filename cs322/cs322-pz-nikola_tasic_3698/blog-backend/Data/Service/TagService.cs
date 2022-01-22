using blog_backend.Util;

namespace blog_backend.Data.Repository;

public interface TagService {
	Tag GetById(int id);
	PaginatedList<Tag> GetAll(Pageable? pageable);
	Tag Save(Tag tag);
	Tag Update(Tag tag);
}