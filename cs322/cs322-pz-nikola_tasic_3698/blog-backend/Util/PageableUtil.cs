namespace blog_backend.Util;

public class PageableUtil {
	// Default page size if one is not provided in the url
	public static readonly int DEFAULT_PAGE_SIZE = 10;

	// Parses page query parameter into a Pageable object that is used for
	// creating PaginatedList objects
	public static Pageable? convert(string? queryString) {
		if (string.IsNullOrEmpty(queryString))
			return null;

		// All values are comma delimited eg. pageNumber,pageSize,sort?
		string[] attrs = queryString.Split(",");

		return new Pageable(
			parsePageNumber(attrs),
			parsePageSize(attrs)
		);
	}

	// private Sort parseSort(String[] attrs) {
	// 	if (attrs.length <= 1)
	// 		return Sort.unsorted();
	//
	// 	String attr;
	// 	if (attrs.length == 3)
	// 		attr = attrs[2];
	// 	else
	// 		attr = attrs[1];
	//
	// 	// if its a valid number dont use it as a sort attribute
	// 	try {
	// 		Integer.parseInt(attr);
	// 		return Sort.unsorted();
	// 	} catch (NumberFormatException ignored) {
	// 		// ignored
	// 	}
	//
	// 	if (attr.startsWith("^")){
	// 		return Sort.by(Sort.Direction.ASC, attr.substring(1));
	// 	} else {
	// 		return Sort.by(Sort.Direction.DESC, attr);
	// 	}
	// }

	// Parses the page number(zero indexed) from the incoming request parameter
	private static int parsePageNumber(String[] attrs) {
		// This shouldn't happen
		if (attrs.Length == 0)
			return 0;

		// Parsing the first comma delimited value from the query string
		return int.TryParse(attrs[0], out int val) ? val : 0;
	}

	// Parses the page size from the incoming request parameter
	private static int parsePageSize(String[] attrs) {
		// Page size is the second value after the comma so if there is less
		// than one element after splitting the query with a comme we 
		// just return the default page size.
		if (attrs.Length <= 1)
			return DEFAULT_PAGE_SIZE;

		// When we implement sorting
		// if (attrs.Length == 3)
			// return int.TryParse(attrs[1], out int val1) ? val1 : DEFAULT_PAGE_SIZE;

		return int.TryParse(attrs[1], out int val2) ? val2 : DEFAULT_PAGE_SIZE;
	}
}