package com.sip.ams.entities;

import jakarta.persistence.*; 

@Entity
@Table(name = "role")
public class Role {
    public Role(String role) {
		super();
		this.role = role;
	}
    
    public Role() {
		super();
		
	}
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "role")
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}