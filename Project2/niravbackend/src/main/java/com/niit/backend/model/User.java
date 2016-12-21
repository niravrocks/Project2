package com.niit.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="raj_user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
	
@Column(unique=true,nullable=false)
private String username;

@Column(nullable=false)
private String password;

@Column(unique=true,nullable=false)
private String email;


private String role;


@Column(name="enabled")
private boolean enabled;


@Column(name="isonline")
private boolean isOnline;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public boolean isStatus() {
	return enabled;
}
public void setStatus(boolean status) {
	this.enabled = status;
}
public boolean isOnline() {
	return isOnline;
}
public void setOnline(boolean isOnline) {
	this.isOnline = isOnline;
}
@Override
public String toString() {
	return this.username + " " + this.email + " " + this.role + "\n";
}
}