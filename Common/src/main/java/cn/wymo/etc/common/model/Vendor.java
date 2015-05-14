package cn.wymo.etc.common.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_vendor")
public class Vendor {
	@Id @GeneratedValue
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	String name;
	@Column(name = "description")
	String description;
	@Column(name = "site")
	String site;
	@Column(name = "created_at")
	Timestamp created_at;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
	public Timestamp getCreatedAt() {
		return created_at;
	}
	
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	public long getId() {
		return id;
	}
	
	
}
