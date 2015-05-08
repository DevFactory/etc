package cn.wymo.etc.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_temperature_soil")
public class TemperatureSoil extends Temperature {
	
	public TemperatureSoil() {
		super();
	}
	
	public TemperatureSoil(Timestamp created_at, Sensor sensor, double value) {
		super(created_at, sensor, value);
	}

}
