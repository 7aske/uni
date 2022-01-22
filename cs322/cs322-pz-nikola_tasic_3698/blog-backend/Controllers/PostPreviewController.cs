using blog_backend.Data;
using blog_backend.Data.Repository;
using blog_backend.Util;
using Microsoft.AspNetCore.Mvc;

namespace blog_backend.Controllers;

// In the original implementation Preview class hides the body of the Post.
// Here we just duplicate the implementation and return the Post object
// as it is super-set of the Preview object.
[ApiController]
[Route("previews")]
public class PostPreviewController : ControllerBase {
	private readonly PostService postService;
	private readonly ILogger<PostPreviewController> logger;

	public PostPreviewController(PostService postService, ILogger<PostPreviewController> logger) {
		this.postService = postService;
		this.logger = logger;
	}

	[HttpGet]
	public IActionResult get(string? page, string? q) {
		Pageable? pageable = PageableUtil.convert(page);
		PaginatedList<Post> posts = postService.GetAll(pageable);
		Response.Headers.Add("X-Data-Count", posts.TotalCount.ToString());
		return Ok(posts);
	}
	
	[HttpGet("all")]
	public IActionResult getAll(string? page, string? q) {
		Pageable? pageable = PageableUtil.convert(page);
		PaginatedList<Post> posts = postService.GetAll(pageable);
		Response.Headers.Add("X-Data-Count", posts.TotalCount.ToString());
		return Ok(posts);
	}
	
	[HttpGet("{id:int}")]
	public IActionResult getById(int id) {
		return Ok(postService.GetById(id));
	}
}