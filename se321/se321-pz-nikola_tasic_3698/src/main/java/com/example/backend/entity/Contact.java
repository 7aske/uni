package com.example.backend.entity;

import com.example.backend.entity.domain.ContactType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * User contact information entry
 */
@Data
@Entity
@Table(name = "contact")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Contact extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "contact_id")
	private Integer id;
	@JoinColumn(name = "user_fk", referencedColumnName = "user_id")
	@ManyToOne
	private User user;
	@Column(name = "value")
	private String value;
	@Column(name = "contact_type")
	@Enumerated(EnumType.STRING)
	private ContactType contactType;
}