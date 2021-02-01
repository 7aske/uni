package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * Blog post tag
 */
@Data
@Entity
@Table(name = "tag")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Tag extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "tag_id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@ManyToMany(mappedBy = "tags")
	@JsonIgnore
	private List<PostPreview> posts;
}