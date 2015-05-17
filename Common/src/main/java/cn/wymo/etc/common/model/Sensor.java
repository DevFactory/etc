package cn.wymo.etc.common.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sensor")
public class Sensor {
	@Id @GeneratedValue
	@Column(name = "id")
	private int id;
	@ManyToOne(cascade=CascadeType.ALL)
	private Vendor vendor;
	@Column(name = "product")
	private int product;
	@Column(name = "category")
	private String category;
	@ManyToOne(cascade=CascadeType.ALL)
	private Gateway gateway;
	
	public Sensor() {
		super();
	}
	
	public int getId() {
		return id;
	}
	
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	public int getProduct() {
		return product;
	}

	public void setProduct(int product) {
		this.product = product;
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

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " {"+
				getId()+ "}";
	}
}
