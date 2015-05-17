package cn.wymo.etc.common.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_gateway")
public class Gateway {
	@Id @GeneratedValue
	@Column(name = "id")
	private int id;
	@ManyToOne(cascade=CascadeType.ALL)
	private Vendor vendor;
	@Column(name = "product")
	private int product;
	@Column(name = "last_sync_at")
	private Timestamp last_sync_at;
	
	public Gateway() {
		super();
	}
	
	public Gateway(int id) {
		super();
		this.id = id;
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
	
	public Timestamp getLastSyncAt() {
		return last_sync_at;
	}

	public void setLastSyncAt(Timestamp last_sync_at) {
		this.last_sync_at = last_sync_at;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " {"+
				getId()+"}";
	}
}
