package it.disim.univaq.sose.project.model;

import java.util.ArrayList;

public class EObject {
	private Long id;
	private String className;
	private ArrayList<EValue> values;
	private Long userId;
	
	public EObject(Long id, String className, Long userId) {
		super();
		this.id = id;
		this.className = className;
		this.values = new ArrayList<EValue>();
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public ArrayList<EValue> getValues() {
		return values;
	}

	public void setValues(ArrayList<EValue> values) {
		this.values = values;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
