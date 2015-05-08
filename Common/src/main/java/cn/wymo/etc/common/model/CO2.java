package cn.wymo.etc.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_co2")
public class CO2 extends Data {
	
	public CO2() {
		super();
	}

	public CO2(Timestamp created_at, Sensor sensor, double value) {
		super(created_at, sensor, value);
		// TODO Auto-generated constructor stub
	}

}
