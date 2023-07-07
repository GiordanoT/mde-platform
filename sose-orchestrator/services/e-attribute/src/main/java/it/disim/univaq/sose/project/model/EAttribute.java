package it.disim.univaq.sose.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
@Table(name = "attributes")
public class EAttribute implements Serializable {

	private static final long serialVersionUID = 1L;
	public static ArrayList<String> types = new ArrayList<String>(Arrays.asList("Boolean", "Number", "String", "Char"));

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", nullable = false, length = 32)
	private String name;
	
	@Column(name = "type", nullable = false, length = 32)
	private String type;
	
	@Column(name = "default_value", nullable = false, length = 32)
	private String defaultValue;
	
	@Column(name = "class_id", nullable = false, length = 20)
	private Long classId;
	
	@Column(name = "user_id", nullable = false, length = 255)
	private Long userId;
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
		
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	
	public String getDefaultValue() { return defaultValue; }
	public void setDefaultValue(String defaultValue) { this.defaultValue = defaultValue; }
	
	public Long getClassId() { return classId; }
	public void setClassId(Long classId) { this.classId = classId; }
	
	public Long getUserId() { return userId; }
	public void setUserId(Long userId) { this.userId = userId; }
	
}
