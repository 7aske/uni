package database.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "post_tag")
public class PostTag implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "id_post_tag")
	private Long idPostTag;

	@Column(name = "id_blog_post")
	private Long idBlogPost;

	@Column(name = "id_tag")
	private Long idTag;

	public PostTag() {
	}

	public Long getIdPostTag() {
		return idPostTag;
	}

	public void setIdPostTag(Long idPostTag) {
		this.idPostTag = idPostTag;
	}

	public Long getIdBlogPost() {
		return idBlogPost;
	}

	public void setIdBlogPost(Long idBlogPost) {
		this.idBlogPost = idBlogPost;
	}

	public Long getIdTag() {
		return idTag;
	}

	public void setIdTag(Long idTag) {
		this.idTag = idTag;
	}

	@Override
	public String toString() {
		return "PostTag{" +
				"idPostTag=" + idPostTag +
				", idBlogPost=" + idBlogPost +
				", idTag=" + idTag +
				'}';
	}
}
