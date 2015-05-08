package cn.wymo.etc.common.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sensor")
public class Sensor {
	@Id
	@Column(name = "id")
	long id;
	@Column(name = "category")
	String category;
	@ManyToOne(cascade=CascadeType.ALL)
	Gateway gateway;
	@Column(name = "status")
	String status;
	
	public Sensor() {
		super();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category.toString();
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status.toString();
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " {"+
				getId()+ "}";
	}
}
