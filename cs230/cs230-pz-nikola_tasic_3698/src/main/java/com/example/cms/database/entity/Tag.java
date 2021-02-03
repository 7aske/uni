package com.example.cms.database.entity;

import com.example.cms.util.UrlUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tag")
public class Tag implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tag")
	private Long idTag;

	@Column(name = "tag_name")
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

	public String getDecodedName() {
		return UrlUtil.decodeValue(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Tag tag = (Tag) o;
		return UrlUtil.decodeValue(name).equals(UrlUtil.decodeValue(tag.name));
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return "Tag{" +
				"idTag=" + idTag +
				", name='" + name + '\'' +
				'}';
	}
}
