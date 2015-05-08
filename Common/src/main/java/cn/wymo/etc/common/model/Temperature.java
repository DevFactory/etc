package cn.wymo.etc.common.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Temperature extends Data {
	
	public Temperature() {
		super();
	}
	
	public Temperature(Timestamp created_at, Sensor sensor, double value) {
		super(created_at, sensor, value);
	}

}
