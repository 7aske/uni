package com.example.cms.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comment")
	private Long idComment;

	@Column(name = "id_post")
	private Long idPost;

	@Column(name = "body")
	private String body;

	@Column(name = "commenter_email")
	private String commenterEmail;

	@Column(name = "commenter_name")
	private String commenterName;

	@Column(name = "date_commented")
	private LocalDate dateCommented;

	public Comment() {
	}

	public Long getIdComment() {
		return idComment;
	}

	public void setIdComment(Long idComment) {
		this.idComment = idComment;
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

	public String getCommenterEmail() {
		return commenterEmail;
	}

	public void setCommenterEmail(String commenterEmail) {
		this.commenterEmail = commenterEmail;
	}

	public String getCommenterName() {
		return commenterName;
	}

	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}

	public LocalDate getDateCommented() {
		return dateCommented;
	}

	public void setDateCommented(LocalDate dateCommented) {
		this.dateCommented = dateCommented;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Comment comment = (Comment) o;
		return idComment.equals(comment.idComment);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idComment);
	}

	@Override
	public String toString() {
		return "Comment{" +
				"idComment=" + idComment +
				", idPost=" + idPost +
				", body='" + body + '\'' +
				", commenterEmail='" + commenterEmail + '\'' +
				", commenterName='" + commenterName + '\'' +
				", dateCommented=" + dateCommented +
				'}';
	}
}
