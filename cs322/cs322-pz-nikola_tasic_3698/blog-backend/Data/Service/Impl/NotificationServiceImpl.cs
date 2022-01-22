using blog_backend.Data.Context;
using blog_backend.Util;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace blog_backend.Data.Repository.Impl;

public class NotificationServiceImpl : NotificationService {
	private readonly AuditableContext context;


	public NotificationServiceImpl(AuditableContext context) {
		this.context = context;
	}

	public PaginatedList<Notification> GetAllByUserId(int userId,
		Pageable? pageable) {
		var source = context
			.Notifications
			.Include("User")
			.Where(comment => comment.UserFk == userId);

		return PaginatedList<Notification>.Create(source, pageable);
	}

	public void MarkAsRead(int userId, int notificationId) {
		Notification? existing = context.Notifications.Find(notificationId);
		if (existing == null) {
			throw new Exception();
		}

		EntityEntry<Notification> entry = context.Entry(existing);
		entry.CurrentValues["Read"] = true;
		context.SaveChanges();
	}

	public PaginatedList<Notification> GetAllUnreadByUserId(int userId,
		Pageable pageable) {
		var source = context
			.Notifications
			.Include("User")
			.Where(notification =>
				notification.UserFk == userId && !notification.Read);

		return PaginatedList<Notification>.Create(source, pageable);
	}
}