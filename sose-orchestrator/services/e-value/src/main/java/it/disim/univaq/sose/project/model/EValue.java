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
@Table(name = "values")
public class EValue implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "instance_of", nullable = false, length = 20)
	private Long instanceOf;
	
	@Column(name = "object_id", nullable = false, length = 20)
	private Long objectId;
	
	@Column(name = "raw", nullable = false, length = 255)
	private String raw;
	
	@Column(name = "user_id", nullable = false, length = 255)
	private Long userId;
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public Long getInstanceOf() { return instanceOf; }
	public void setInstanceOf(Long instanceOf) { this.instanceOf = instanceOf; }
	
	public Long getObjectId() { return objectId; }
	public void setObjectId(Long objectId) { this.objectId = objectId; }
	
	public String getRaw() { return raw; }
	public void setRaw(String raw) { this.raw = raw; }
	
	public Long getUserId() { return userId; }
	public void setUserId(Long userId) { this.userId = userId; }
	
}
