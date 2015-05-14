package cn.wymo.etc.dealer.processors;

import java.sql.Timestamp;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.wymo.etc.common.model.Category;
import cn.wymo.etc.common.model.Data;
import cn.wymo.etc.common.model.Gateway;
import cn.wymo.etc.common.model.Sensor;
import cn.wymo.etc.dealer.App;
import cn.wymo.etc.dealer.HibernateUtil;
import cn.wymo.etc.dealer.IDataProcessor;

public class DB implements IDataProcessor {
	static final Logger logger = Logger.getLogger(App.class);
	static private String name = "mysql";
	private JSONObject input;
	private JSONObject output;
	private Session session;
	private Transaction tx;
	
	public DB() {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.getTransaction();
		if (tx == null || !tx.isActive()) {
			tx = session.beginTransaction();
		}
		if(input != null) {
			input = new JSONObject();
			input = null;
		}
		
		if(output != null) {
			output = new JSONObject();
			output = null;
		}
	}
	
	public boolean init() {
		@SuppressWarnings("deprecation")
		SessionFactory sf = new Configuration().configure("/hibernate_init.cfg.xml").buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		tx.commit();
		session.close();
		sf.close();
		return true;
	}

	public String getName() {
		return name;
	}

	public void setInput(JSONObject input) {
		this.input = input;
	}

	@SuppressWarnings("unused")
	public boolean process() {
		try {
			Iterator<String> itG = input.keys();
			while(itG.hasNext()) {
				String gstr = itG.next();
				long gid = Long.parseLong(gstr);
				Gateway gateway = null;
				Query qg = session.createQuery("from Gateway where id=:id");
				qg.setLong("id", gid);

				gateway = (Gateway) qg.list().get(0);
				JSONObject sensors  = input.getJSONObject(gstr);
				Iterator<String> itS = sensors.keys();
				while(itS.hasNext()) {
					String sStr = itS.next();
					long sid = Long.parseLong(sStr);
					Sensor sensor = null;
					Query qs = session.createQuery("from Gateway where id=:id");
					qs.setLong("id", sid);

					sensor = (Sensor) qs.list().get(0);
					JSONArray values  = sensors.getJSONArray(sStr);
					for(int i=0;i<values.length();i++) {
						JSONObject val = values.getJSONObject(i);
						long t = val.getLong("timestamp");
						double v = val.getDouble("value");
						Category category = Category.getEnum(sensor.getCategory());
			        	Class<? extends Data> klass = category.getModelClass();
						Data data = klass.getConstructor(Timestamp.class, Sensor.class, double.class).newInstance(t, sensor, v);
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

	public JSONObject getOutput() {
		return this.output;
	}

}
