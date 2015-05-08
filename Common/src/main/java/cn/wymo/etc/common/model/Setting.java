package cn.wymo.etc.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_setting")
public class Setting {
	@Id @GeneratedValue
	@Column(name = "id")
	int id;
	@Column(name = "key")
	String key;
	@Column(name = "value")
	String value;
	
	public Setting() {
		super();
	}
	
	public Setting(String key, String value) {
		super();
		this.key   = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String key) {
		this.value = key;
	}
}
