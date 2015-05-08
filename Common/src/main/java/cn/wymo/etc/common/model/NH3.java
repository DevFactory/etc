package cn.wymo.etc.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_nh3")
public class NH3 extends Data {

	public NH3() {
		super();
	}
	
	public NH3(Timestamp created_at, Sensor sensor, double value) {
		super(created_at, sensor, value);
		// TODO Auto-generated constructor stub
	}

}

