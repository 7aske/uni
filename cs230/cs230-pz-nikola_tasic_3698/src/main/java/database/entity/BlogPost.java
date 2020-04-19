package database.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "blog_post")
public class BlogPost {
	@Id
	@GeneratedValue
	@Column(name = "id_blog_post")
	private Long idBlogPost;

	@Column(name = "body")
	private String body;

	@Column(name = "title")
	private String title;

	@Column(name = "slug")
	private String slug;

	@Column(name = "published")
	private boolean published;

	@Column(name = "date_posted")
	private LocalDate datePosted;

	@Column(name = "author")
	private String author;

	@Column(name = "preview")
	private String preview;

	@OneToMany
	@JoinTable(name = "tag",
			joinColumns = @JoinColumn(name = "id_blog_post"),
			inverseJoinColumns = @JoinColumn(name = "id_post_tag")
	)
	private Set<PostTag> postTags;

	public BlogPost() {
	}

	public Long getIdBlogPost() {
		return idBlogPost;
	}

	public void setIdBlogPost(Long idBlogPost) {
		this.idBlogPost = idBlogPost;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDate getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public Set<PostTag> getPostTags() {
		return postTags;
	}

	public void setPostTags(Set<PostTag> postTags) {
		this.postTags = postTags;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	@Override
	public String toString() {
		return "BlogPost{" +
				"idBlogPost=" + idBlogPost +
				", body='" + body + '\'' +
				", title='" + title + '\'' +
				", slug='" + slug + '\'' +
				", published=" + published +
				", datePosted=" + datePosted +
				", author='" + author + '\'' +
				", preview='" + preview + '\'' +
				", postTags=" + postTags +
				'}';
	}
}
