using blog_backend.Data;
using blog_backend.Data.Repository;
using blog_backend.Util;
using Microsoft.AspNetCore.Mvc;

namespace blog_backend.Controllers; 

[ApiController]
[Route("tags")]
public class TagController : ControllerBase {
	
	private readonly TagService tagService;
	private readonly ILogger<TagController> logger;

	public TagController(TagService tagService, ILogger<TagController> logger) {
		this.tagService = tagService;
		this.logger = logger;
	}

	[HttpGet]
	public IActionResult get(string? page, string? q) {
		Pageable? pageable = PageableUtil.convert(page);
		PaginatedList<Tag> tags = tagService.GetAll(pageable);
		Response.Headers.Add("X-Data-Count", tags.TotalCount.ToString());
		return Ok(tags);
	}
	
	[HttpPost]
	public IActionResult save([FromBody] Tag tag) {
		return Ok(tagService.Save(tag));
	}
	
	[HttpPut]
	public IActionResult update([FromBody] Tag tag) {
		return Ok(tagService.Update(tag));
	}
}