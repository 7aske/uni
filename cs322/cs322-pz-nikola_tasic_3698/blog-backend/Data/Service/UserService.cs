using blog_backend.Util;

namespace blog_backend.Data.Repository;

public interface UserService {
	User GetByUsername(string username);
	bool IsPasswordValid(string plain, string hashed);
	User GetById(int id);
	PaginatedList<User> GetAll(Pageable? pageable);
}