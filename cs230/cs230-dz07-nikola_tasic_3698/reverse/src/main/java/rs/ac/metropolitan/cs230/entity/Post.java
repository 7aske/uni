package rs.ac.metropolitan.cs230.entity;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_post")
	public Long idPost;

	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	@ManyToOne
	public User idUser;

	@Column(name = "title")
	public String title;
	@Column(name = "excerpt")
	public String excerpt;
	@Column(name = "body")
	public String body;
	@Column(name = "date_posted")
	public LocalDate datePosted;
	@Column(name = "published")
	public boolean published;
	@Column(name = "slug")
	public String slug;

	public Post() {}
	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}


	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
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


	public boolean getPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}


	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

}