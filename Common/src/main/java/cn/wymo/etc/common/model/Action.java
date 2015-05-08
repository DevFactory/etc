package cn.wymo.etc.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_action")
public class Action {
	@Id @GeneratedValue
	@Column(name = "id")
	int id;
	@Column(name = "name")
	String name;
	@Column(name = "label")
	String label;
	@Column(name = "description")
	String description;
	@Column(name = "commands")
	String[] commands;
	@Column(name = "verifications")
	String[] verifications;
	
	public Action() {
		super();
	}
	
	public Action(String name, String label, String description) {
		super();
		this.name = name;
		this.label = label;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String[] getCommands() {
		return commands;
	}
	
	public void setCommands(String[] commands) {
		
		this.commands = commands;
	}
	
	public String[] getVerifications() {
		return verifications;
	}
	
	public void setVerification(String[] verifications) {
		this.verifications = verifications;
	}
	
	public int getId() {
		return id;
	}
	
}
