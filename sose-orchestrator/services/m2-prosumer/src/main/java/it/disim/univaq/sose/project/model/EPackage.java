package it.disim.univaq.sose.project.model;

import java.util.ArrayList;

public class EPackage {
	private Long id;
	private String name;
	private String uri;
	private ArrayList<EClass> classes;
	private Long userId;
	
	public EPackage(Long id, String name, String uri, Long userId) {
		this.id = id;
		this.name = name;
		this.uri = uri;
		this.classes = new ArrayList<EClass>();
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
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public ArrayList<EClass> getClasses() {
		return classes;
	}
	
	public void setClasses(ArrayList<EClass> classes) {
		this.classes = classes;
	}
	
	public void addClass(EClass classifier) {
		this.classes.add(classifier);
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
