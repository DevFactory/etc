package cn.wymo.etc.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_temperature_air")
public class TemperatureAir extends Temperature {
	
	public TemperatureAir() {
		super();
	}
	
	public TemperatureAir(Timestamp created_at, Sensor sensor, double value) {
		super(created_at, sensor, value);
	}

}
