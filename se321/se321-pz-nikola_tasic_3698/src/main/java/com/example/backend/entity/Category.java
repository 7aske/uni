package com.example.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Blog post category
 */
@Data
@Entity
@Table(name = "category")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Category extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "category_id")
	private Integer id;
	@Column(name = "name")
	private String name;
}