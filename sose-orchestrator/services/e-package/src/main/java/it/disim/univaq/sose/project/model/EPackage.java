package it.disim.univaq.sose.project.model;

import java.io.Serializable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "packages")
public class EPackage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", nullable = false, length = 32)
	private String name;
	
	@Column(name = "uri", nullable = false, length = 255)
	private String uri;
	
	@Column(name = "user_id", nullable = false, length = 255)
	private Long userId;
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getUri() { return uri; }
	public void setUri(String uri) { this.uri = uri; }
	
	public Long getUserId() { return userId; }
	public void setUserId(Long userId) { this.userId = userId; }
}
