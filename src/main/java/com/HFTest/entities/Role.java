package com.HFTest.entities;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document
public class Role implements Serializable, GrantedAuthority{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private Set<Permission> permissions;
	
	
	public Role(String name) {
		super();
		this.name = name;
	}
	public Role() {
		super();
	}
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getAuthority() {
		return getName();
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", permissions=" + permissions + "]";
	}
	
}