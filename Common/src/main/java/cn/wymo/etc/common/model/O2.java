package cn.wymo.etc.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_o2")
public class O2 extends Data {
	
	public O2() {
		super();
	}

	public O2(Timestamp created_at, Sensor sensor, double value) {
		super(created_at, sensor, value);
		// TODO Auto-generated constructor stub
	}

}
