using blog_backend.Data.Context;
using blog_backend.Util;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace blog_backend.Data.Repository.Impl;

public class CategoryServiceImpl : CategoryService {
	private readonly AuditableContext context;


	public CategoryServiceImpl(AuditableContext context) {
		this.context = context;
	}

	public Category GetById(int id) {
		return context.Categories.Single(e => e.Id == id);
	}
	
	public PaginatedList<Category> GetAll(Pageable? pageable) {
		return PaginatedList<Category>.Create(context.Categories, pageable);
	}

	public Category Save(Category category) {
		category.CreatedDate = DateTime.Now;
		EntityEntry<Category> entry = context.Categories.Add(category);
		context.SaveChanges();
		return entry.Entity;
	}

	
	public Category Update(Category category) {
		Category? existing = context.Categories.Find(category.Id);
		if (existing == null) {
			throw new Exception();
		}

		EntityEntry<Category> entry = context.Entry(existing);
		entry.CurrentValues.SetValues(category);
		entry.CurrentValues["CreatedDate"] = existing.CreatedDate;
		context.SaveChanges();
		return entry.Entity;
	}
}