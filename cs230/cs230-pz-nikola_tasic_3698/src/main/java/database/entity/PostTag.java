package database.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

public class PostTag implements Serializable {
	@Id
	@GeneratedValue
	@Column
	private Long idPostTag;
}
