package com.example.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Uploaded image shown on the blog post
 */
@Data
@Entity
@Table(name = "media")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Media extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "media_id")
	private Integer id;
	@Column(name = "uri")
	private String uri;
}