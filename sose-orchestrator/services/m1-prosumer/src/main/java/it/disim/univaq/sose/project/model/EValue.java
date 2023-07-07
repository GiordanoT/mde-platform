package it.disim.univaq.sose.project.model;

public class EValue {
	private Long id;
	private String attributeName;
	private String raw;
	private Long userId;
	
	public EValue(Long id, String attributeName, String raw, Long userId) {
		super();
		this.id = id;
		this.attributeName = attributeName;
		this.raw = raw;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAttributeName() {
		return attributeName;
	}
	
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	public String getRaw() {
		return raw;
	}
	
	public void setRaw(String raw) {
		this.raw = raw;
	}
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
