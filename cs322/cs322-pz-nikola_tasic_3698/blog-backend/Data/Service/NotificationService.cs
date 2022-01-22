using blog_backend.Util;

namespace blog_backend.Data.Repository;

public interface NotificationService {
	PaginatedList<Notification> GetAllByUserId(int userId, Pageable? pageable);
	void MarkAsRead(int userId, int notificationId);
	PaginatedList<Notification> GetAllUnreadByUserId(int userId, Pageable pageable);
}