package it.disim.univaq.sose.project.model;

import java.util.ArrayList;

public class EClass {
	private Long id;
	private String name;
	private boolean isAbstract;
	private ArrayList<EAttribute> attributes;
	private Long userId;
	
	public EClass(Long id, String name, boolean isAbstract, Long userId) {
		this.id = id;
		this.name = name;
		this.isAbstract = isAbstract;
		this.attributes = new ArrayList<EAttribute>();
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
	
	public boolean isAbstract() {
		return isAbstract;
	}
	
	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
	
	public ArrayList<EAttribute> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(ArrayList<EAttribute> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute(EAttribute attribute) {
		this.attributes.add(attribute);
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
}
