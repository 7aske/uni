using blog_backend.Data.Repository;
using Microsoft.AspNetCore.Mvc;

namespace blog_backend.Controllers; 

[ApiController]
[Route("comments")]
public class CommentController : ControllerBase {
	
	private readonly CommentService commentService;
	private readonly ILogger<CommentController> logger;

	public CommentController(CommentService commentService, ILogger<CommentController> logger) {
		this.commentService = commentService;
		this.logger = logger;
	}
}