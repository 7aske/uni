using blog_backend.Util;

namespace blog_backend.Data.Repository;

public interface CategoryService {
	Category GetById(int id);
	PaginatedList<Category> GetAll(Pageable? pageable);
	Category Save(Category category);
	Category Update(Category category);
}