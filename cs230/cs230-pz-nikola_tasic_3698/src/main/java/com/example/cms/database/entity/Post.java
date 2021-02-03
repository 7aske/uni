package com.example.cms.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_post")
	private Long idPost;

	@Column(name = "body")
	private String body;

	@Column(name = "excerpt")
	private String excerpt;

	@Column(name = "title")
	private String title;

	@Column(name = "slug")
	private String slug;

	@Column(name = "date_posted")
	private LocalDate datePosted;

	@Column(name = "published")
	private Boolean published;

	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	@ManyToOne
	private User idUser;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "id_post"), inverseJoinColumns = @JoinColumn(name = "id_tag"))
	private Set<Tag> tags = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_post")
	private List<Comment> commentList = new ArrayList<>();

	public Post() {
	}

	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
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

	public LocalDate getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	@Override
	public String toString() {
		return "Post{" +
				"idPost=" + idPost +
				", body='" + body + '\'' +
				", excerpt='" + excerpt + '\'' +
				", title='" + title + '\'' +
				", slug='" + slug + '\'' +
				", datePosted=" + datePosted +
				", published=" + published +
				", idUser=" + idUser +
				", tags=" + tags +
				", commentList=" + commentList +
				'}';
	}
}
