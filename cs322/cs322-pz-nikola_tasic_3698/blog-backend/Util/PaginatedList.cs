using blog_backend.Data;

namespace blog_backend.Util;

/**
 * Wrapper that allows us to carry page related data when returning items from
 * repositories.
 */
public class PaginatedList<T> : List<T> {
	public int PageNumber { get; private set; }
	public int PageSize { get; private set; }
	public int TotalPages { get; private set; }
	public int TotalCount { get; private set; }

	public PaginatedList(List<T> items, int count, int pageNumber,
		int pageSize) {
		PageNumber = pageNumber;
		PageSize = pageSize;
		TotalPages = (int) Math.Ceiling(count / (double) pageSize);
		TotalCount = count;
		
		AddRange(items);
	}

	public static PaginatedList<T> Create(IQueryable<T> source,
		Pageable? pageable) {
		
		int count = source.Count();
		int pageNumber = pageable?.PageNumber ?? 0;
		int pageSize = pageable?.PageSize ?? count;
		
		List<T> items;
		
		if (pageable == null) {
			items = source.ToList();
		} else {
			items = source.Skip(pageNumber * pageSize).Take(pageSize)
				.ToList();
		}
		
		return new PaginatedList<T>(items, count, pageNumber, pageSize);
	}
}