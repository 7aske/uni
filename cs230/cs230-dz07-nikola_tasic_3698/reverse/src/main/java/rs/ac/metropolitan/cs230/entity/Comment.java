package rs.ac.metropolitan.cs230.entity;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comment")
	public Long idComment;

	@JoinColumn(name = "id_post", referencedColumnName = "id_post")
	@ManyToOne
	public Post idPost;

	@Column(name = "body")
	public String body;
	@Column(name = "commenter_name")
	public String commenterName;
	@Column(name = "commenter_email")
	public String commenterEmail;
	@Column(name = "date_commented")
	public LocalDate dateCommented;

	public Comment() {}
	public Long getIdComment() {
		return idComment;
	}

	public void setIdComment(Long idComment) {
		this.idComment = idComment;
	}


	public Post getIdPost() {
		return idPost;
	}

	public void setIdPost(Post idPost) {
		this.idPost = idPost;
	}


	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}


	public String getCommenterName() {
		return commenterName;
	}

	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}


	public String getCommenterEmail() {
		return commenterEmail;
	}

	public void setCommenterEmail(String commenterEmail) {
		this.commenterEmail = commenterEmail;
	}


	public LocalDate getDateCommented() {
		return dateCommented;
	}

	public void setDateCommented(LocalDate dateCommented) {
		this.dateCommented = dateCommented;
	}

}