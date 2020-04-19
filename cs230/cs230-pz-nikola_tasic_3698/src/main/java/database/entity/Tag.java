package database.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tag")
public class Tag implements Serializable {
	@Id
	@GeneratedValue
	@Column(name = "id_tag")
	private Long idTag;

	@Column(name = "name")
	private String name;

	public Tag() {
	}

	public Long getIdTag() {
		return idTag;
	}

	public void setIdTag(Long idTag) {
		this.idTag = idTag;
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
				"idTag=" + idTag +
				", name='" + name + '\'' +
				'}';
	}
}
