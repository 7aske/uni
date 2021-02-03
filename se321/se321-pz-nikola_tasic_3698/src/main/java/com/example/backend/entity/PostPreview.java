package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * Blog post preview
 */
@Data
@Entity
@Table(name = "post")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class PostPreview extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "post_id")
	private Integer id;
	@JoinColumn(name = "user_fk", referencedColumnName = "user_id")
	@ManyToOne
	private User user;
	@JoinColumn(name = "category_fk", referencedColumnName = "category_id")
	@ManyToOne
	private Category category;
	@Column(name = "title")
	private String title;
	@Column(name = "excerpt")
	private String excerpt;
	@Column(name = "deleted")
	@JsonIgnore
	private boolean deleted;
	@Column(name = "published")
	@JsonIgnore
	private boolean published;
	@Column(name = "views")
	private Integer views;
	@Column(name = "slug")
	private String slug;
	@ManyToMany
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_fk"), inverseJoinColumns = @JoinColumn(name = "tag_fk"))
	private List<Tag> tags;
}