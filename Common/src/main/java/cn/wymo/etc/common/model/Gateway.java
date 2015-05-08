package cn.wymo.etc.common.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_gateway")
public class Gateway {
	@Id
	@Column(name = "id")
	long id;
	@Column(name = "status")
	String status;
	@Column(name = "host")
	String host = "192.168.100.230";
	@Column(name = "last_sync_at")
	Timestamp last_sync_at;
	
	public Gateway() {
		super();
	}
	
	public Gateway(int id) {
		super();
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public Timestamp getLastSyncAt() {
		return last_sync_at;
	}

	public void setLastSyncAt(Timestamp last_sync_at) {
		this.last_sync_at = last_sync_at;
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
				getId()+"}";
	}
}
