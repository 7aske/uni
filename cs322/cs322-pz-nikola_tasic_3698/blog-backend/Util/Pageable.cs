namespace blog_backend.Util; 

// Utility that holds page information parsed from the page request parameter
public class Pageable {
	public int PageNumber { get; }
	public int PageSize { get; }

	public Pageable(int pageNumber, int pageSize) {
		PageNumber = pageNumber;
		PageSize = pageSize;
	}

	public override string ToString() {
		return $"Pageable{{ number: {PageNumber}, size: {PageSize}}}";
	}
}