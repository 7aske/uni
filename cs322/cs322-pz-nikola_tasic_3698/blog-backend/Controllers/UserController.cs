using System.Security.Claims;
using blog_backend.Data;
using blog_backend.Data.Repository;
using blog_backend.Util;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace blog_backend.Controllers;

[ApiController]
[Route("users")]
public class UserController : ControllerBase {
	private readonly ILogger<UserController> logger;
	private readonly UserService userService;
	private readonly NotificationService notificationService;
	private readonly IHttpContextAccessor httpContextAccessor;

	public UserController(UserService userService,
		IHttpContextAccessor httpContextAccessor,
		ILogger<UserController> logger,
		NotificationService notificationService) {
		this.userService = userService;
		this.httpContextAccessor = httpContextAccessor;
		this.logger = logger;
		this.notificationService = notificationService;
	}

	[HttpGet]
	public IActionResult get(string? page, string? q) {
		Pageable? pageable = PageableUtil.convert(page);
		PaginatedList<User> users = userService.GetAll(pageable);
		Response.Headers.Add("X-Data-Count", users.TotalCount.ToString());
		return Ok(users);
	}

	[HttpGet("{identifier}")]
	public IActionResult getById(string identifier) {
		return Ok(int.TryParse(identifier, out int val)
			? userService.GetById(val)
			: userService.GetByUsername(identifier));
	}

	[Authorize]
	[HttpPut]
	public IActionResult update() {
		logger.LogWarning("PUT /users Not Implemented");
		return StatusCode(503);
	}

	[Authorize]
	[HttpPut("password")]
	public IActionResult changePassword() {
		logger.LogWarning("PUT /users/password Not Implemented");
		return StatusCode(503);
	}

	[Authorize]
	[HttpGet("notifications")]
	public IActionResult getNotifications(string? page, bool all = false) {
		Pageable? pageable = PageableUtil.convert(page);
		var userId =
			int.Parse(
				httpContextAccessor.HttpContext?.User.FindFirstValue(ClaimTypes
					.NameIdentifier) ?? throw new Exception());
		PaginatedList<Notification> notifications;
		if (all) {
			notifications = notificationService.GetAllByUserId(userId, pageable);
		} else {
			notifications = notificationService.GetAllUnreadByUserId(userId, pageable);
		}

		return Ok(notifications);
	}

	[Authorize]
	[HttpGet("{identifier}/roles")]
	public IActionResult getRolesById(string identifier) {
		return Ok(new List<Role> {new("ROLE_ADMIN"), new("ROLE_USER")});
	}
}