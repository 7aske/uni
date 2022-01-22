using blog_backend.Data.Context;
using blog_backend.Util;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace blog_backend.Data.Repository.Impl;

public class TagServiceImpl : TagService {
	private readonly AuditableContext context;


	public TagServiceImpl(AuditableContext context) {
		this.context = context;
	}

	public Tag GetById(int id) {
		return context.Tags.Single(e => e.Id == id);
	}
	
	public PaginatedList<Tag> GetAll(Pageable? pageable) {
		return PaginatedList<Tag>.Create(context.Tags, pageable);
	}
	

	public Tag Save(Tag tag) {
		EntityEntry<Tag> entry = context.Tags.Add(tag);
		context.SaveChanges();
		return entry.Entity;
	}

	
	public Tag Update(Tag tag) {
		Tag? existing = context.Tags.Find(tag.Id);
		if (existing == null) {
			throw new Exception();
		}

		EntityEntry<Tag> entry = context.Entry(existing);
		entry.CurrentValues.SetValues(tag);
		entry.CurrentValues["CreatedDate"] = existing.CreatedDate;
		context.SaveChanges();
		return entry.Entity;
	}
}