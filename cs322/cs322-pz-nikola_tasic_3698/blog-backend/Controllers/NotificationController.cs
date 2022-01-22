using System.Security.Claims;
using blog_backend.Data;
using blog_backend.Data.Repository;
using blog_backend.Util;
using Microsoft.AspNetCore.Mvc;

namespace blog_backend.Controllers;

[ApiController]
[Route("notifications")]
public class NotificationController : ControllerBase {
	private readonly NotificationService notificationService;
	private readonly IHttpContextAccessor httpContextAccessor;
	private readonly ILogger<NotificationController> logger;

	public NotificationController(NotificationService notificationService,
		IHttpContextAccessor httpContextAccessor,
		ILogger<NotificationController> logger) {
		this.notificationService = notificationService;
		this.httpContextAccessor = httpContextAccessor;
		this.logger = logger;
	}

	[HttpPut("{notificationId:int}/read")]
	public IActionResult get(int notificationId) {
		string? userIdStr = httpContextAccessor.HttpContext?.User
			.FindFirst(ClaimTypes.NameIdentifier)
			?.Value;
		if (userIdStr == null) {
			return BadRequest();
		}
		int userId = int.Parse(userIdStr);
		notificationService.MarkAsRead(userId, notificationId);
		return Ok();
	}
}