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
@Table(name = "classes")
public class EClass implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", nullable = false, length = 32)
	private String name;
	
	@Column(name = "is_abstract", nullable = false, length = 1)
	private Boolean isAbstract;
	
	@Column(name = "package_id", nullable = false, length = 20)
	private Long packageId;
	
	@Column(name = "user_id", nullable = false, length = 255)
	private Long userId;
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public Boolean getIsAbstract() { return isAbstract; }
	public void setIsAbstract(Boolean isAbstract) { this.isAbstract = isAbstract; }
	
	public Long getPackageId() { return packageId; }
	public void setPackageId(Long packageId) { this.packageId = packageId; }
	
	public Long getUserId() { return userId; }
	public void setUserId(Long userId) { this.userId = userId; }
	
}
