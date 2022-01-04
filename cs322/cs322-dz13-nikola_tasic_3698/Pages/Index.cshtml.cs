using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace cs322_dz13_nikola_tasic_3698.Pages;

public class IndexModel : PageModel {
	private readonly ILogger<IndexModel> _logger;

	public IndexModel(ILogger<IndexModel> logger) {
		_logger = logger;
	}
}