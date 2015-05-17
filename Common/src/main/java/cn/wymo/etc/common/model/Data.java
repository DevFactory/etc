package cn.wymo.etc.common.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.apache.commons.collections.Transformer;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Data implements Comparable<Data> {
	@Id @GeneratedValue
	@Column(name = "id")
	private long id;
	@Column(name = "created_at")
	private Timestamp created_at;
	@ManyToOne(cascade=CascadeType.ALL)
	private Sensor sensor;
	@Column(name = "value")
	private double value;
	
	static public Transformer DATA_TO_NUMBER = new Transformer() {
		public Object transform(Object obj) {
			if(obj instanceof Data) {
				return new Double(((Data) obj).getValue());
			}
			return null;
		}
	};
	
	public Data() {
		super();
	}
	
	public Data(Timestamp created_at, Sensor sensor, double value) {
		super();
		this.created_at = created_at;
		this.sensor = sensor;
		this.value = value;
	}

	public Timestamp getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public double getValue() {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(1, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public void setValue(double value) {
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public int compareTo(Data val) {
		Double s = new Double(getValue());
		Double d = new Double(val.getValue());
		return s.compareTo(d);
	}

	@Override
	public String toString() { 
		return (new Double(getValue())).toString();
	}

}
