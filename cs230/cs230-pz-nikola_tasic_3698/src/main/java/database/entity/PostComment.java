package database.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "post_comment")
public class PostComment {
	@Id
	@GeneratedValue
	@Column(name = "id_post_comment")
	private Long idPostComment;

	@Column(name = "author")
	private String author;

	@Column(name = "body")
	private String body;

	@Column(name = "date_posted")
	private LocalDate datePosted;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id_blog_post", name = "id_blog_post")
	private BlogPost blogPost;

	public PostComment() {
	}

	public Long getIdPostComment() {
		return idPostComment;
	}

	public void setIdPostComment(Long idPostComment) {
		this.idPostComment = idPostComment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public BlogPost getBlogPost() {
		return blogPost;
	}

	public void setBlogPost(BlogPost blogPost) {
		this.blogPost = blogPost;
	}

	@Override
	public String toString() {
		return "PostComment{" +
				"idPostComment=" + idPostComment +
				", author='" + author + '\'' +
				", body='" + body + '\'' +
				", datePosted=" + datePosted +
				", blogPost=" + blogPost.getIdBlogPost() +
				'}';
	}
}
