using blog_backend.Data;
using blog_backend.Data.Repository;
using blog_backend.Util;
using Microsoft.AspNetCore.Mvc;

namespace blog_backend.Controllers; 

[ApiController]
[Route("categories")]
public class CategoryController : ControllerBase {
	
	private readonly CategoryService categoryService;
	private readonly ILogger<CategoryController> logger;

	public CategoryController(CategoryService categoryService, ILogger<CategoryController> logger) {
		this.categoryService = categoryService;
		this.logger = logger;
	}

	[HttpGet]
	public IActionResult get(string? page, string? q) {
		Pageable? pageable = PageableUtil.convert(page);
		PaginatedList<Category> posts = categoryService.GetAll(pageable);
		Response.Headers.Add("X-Data-Count", posts.TotalCount.ToString());
		return Ok(posts);
	}
	
	[HttpPost]
	public IActionResult save([FromBody] Category category) {
		return Ok(categoryService.Save(category));
	}
	
	[HttpPut]
	public IActionResult update([FromBody] Category category) {
		return Ok(categoryService.Update(category));
	}
}