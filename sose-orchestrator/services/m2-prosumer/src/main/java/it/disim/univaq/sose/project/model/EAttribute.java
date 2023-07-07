package it.disim.univaq.sose.project.model;


public class EAttribute {
	private Long id;
	private String name;
	private String type;
	private String defaultValue;
	private Long userId;
	
	public EAttribute(Long id, String name, String type, String defaultValue, Long userId) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.defaultValue = defaultValue;
		this.userId = userId;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
