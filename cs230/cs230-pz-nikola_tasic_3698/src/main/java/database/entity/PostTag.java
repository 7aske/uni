package database.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "post_tag")
public class PostTag {
	@Id
	@GeneratedValue
	@Column(name = "id_post_tag")
	private Long idPostTag;

	@Column(name = "name")
	private String name;

	public PostTag() {
	}

	public Long getIdPostTag() {
		return idPostTag;
	}

	public void setIdPostTag(Long idPostTag) {
		this.idPostTag = idPostTag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PostTag{" +
				"idPostTag=" + idPostTag +
				", name='" + name + '\'' +
				'}';
	}
}
