using blog_backend.Data;
using blog_backend.Data.Repository;
using blog_backend.Util;
using Microsoft.AspNetCore.Mvc;

namespace blog_backend.Controllers;

[ApiController]
[Route("posts")]
public class PostController : ControllerBase {
	private readonly ILogger<PostController> logger;
	private readonly PostService postService;
	private readonly CommentService commentService;

	public PostController(PostService postService, ILogger<PostController> logger, CommentService commentService) {
		this.postService = postService;
		this.logger = logger;
		this.commentService = commentService;
	}

	[HttpGet]
	public IActionResult get(string? page, string? q) {
		Pageable? pageable = PageableUtil.convert(page);
		return Ok(postService.GetAll(pageable));
	}
	
	[HttpGet("{identifier}")]
	public IActionResult getById(string identifier) {
		return Ok(int.TryParse(identifier, out int val)
			? postService.GetById(val)
			: postService.GetBySlug(identifier));
	}
	
	
	[HttpPost]
	public IActionResult save([FromBody] Post post) {
		return Ok(postService.Save(post));
	}
	
	[HttpPut]
	public IActionResult update([FromBody] Post post) {
		return Ok(postService.Update(post));
	}
	
	[HttpGet("summary")]
	public IActionResult getSummary() {
		logger.LogWarning("GET /posts/summary Not Implemented");
		return StatusCode(503);
	}
	
	[HttpGet("{postId:int}/comments")]
	public IActionResult getCommentsForPost(string page, int postId) {
		Pageable? pageable = PageableUtil.convert(page);
		return Ok(commentService.GetAllByPostId(postId, pageable));
	}
	
	[HttpPost("{postId:int}/comments")]
	public IActionResult saveCommentForPost(int postId, [FromBody] Comment comment) {
		return Ok(commentService.SaveForPost(postId, comment));
	}
}