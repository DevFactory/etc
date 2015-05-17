package cn.wymo.etc.dealer.processors;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.wymo.etc.common.model.Category;
import cn.wymo.etc.common.model.Data;
import cn.wymo.etc.common.model.Sensor;
import cn.wymo.etc.dealer.App;
import cn.wymo.etc.dealer.HibernateUtil;
import cn.wymo.etc.dealer.IDataProcessor;

public class DB implements IDataProcessor {
	static final Logger logger = Logger.getLogger(App.class);
	static private String name = "mysql";
	private JSONArray input;
	private JSONObject output;
	private Session session;
	private Transaction tx;
	
	public DB() {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.getTransaction();
		if (tx == null || !tx.isActive()) {
			tx = session.beginTransaction();
		}
		
		if(output != null) {
			output = new JSONObject();
			output = null;
		}
	}

	public String getName() {
		return name;
	}

	public void setInput(Object input) {
		this.input = (JSONArray) input;
	}

	@SuppressWarnings("unused")
	public boolean process() {
		try {
			JSONArray gateways = this.input;
			for(int i=0;i<gateways.length();i++) {
				JSONObject g = gateways.getJSONObject(i);
				/* Now I don't care the which gateway the data is coming from.
				int gvd = g.getInt("vendor");
				int gpd = g.getInt("product");
				*/
				JSONArray sensors = g.getJSONArray("sensors");
				for(int j=0;j<sensors.length();j++) {
					JSONObject s = sensors.getJSONObject(j);
					
					int svd = g.getInt("vendor");
					int spd = g.getInt("product");
					JSONArray values = g.getJSONArray("values");
					
					Query qs = session.createQuery("from Sensor where vendor_id=:vendor and product=:product");
					qs.setInteger("vendor", svd);
					qs.setInteger("product", spd);
					Sensor sensor = (Sensor) qs.list().get(0);
					for(int k=0;k<values.length();k++) {
						JSONObject v = values.getJSONObject(k);
						long t = v.getLong("timestamp");
						double value = v.getDouble("value");
						Category category = Category.getEnum(sensor.getCategory());
			        	Class<? extends Data> klass = category.getModelClass();
						Data data = klass.getConstructor(Timestamp.class, Sensor.class, double.class).newInstance(t, sensor, value);
						session.save(data);
					}
				}
			}
			tx.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return false;
	}

	public Object getOutput() {
		return this.output;
	}

}
