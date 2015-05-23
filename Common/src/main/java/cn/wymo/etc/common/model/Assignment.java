package cn.wymo.etc.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_assignment")
public class Assignment {
	@Id @GeneratedValue
	@Column(name = "id")
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	@Column(name = "role")
	private String role;
	
	public Assignment() {
		
	}
	
	public Assignment(User user, String role) {
		setUser(user);
		setRole(role);
	}
	
	public long getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
}
