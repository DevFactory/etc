package cn.wymo.etc.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_dust")
public class Dust extends Data {

	public Dust() {
		super();
	}
	
	public Dust(Timestamp created_at, Sensor sensor, double value) {
		super(created_at, sensor, value);
		// TODO Auto-generated constructor stub
	}

}
