package cn.wymo.etc.producerUI.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vaadin.server.VaadinSession;

import cn.wymo.etc.producerUI.HibernateUtil;
import cn.wymo.etc.common.model.User;
import cn.wymo.etc.common.model.Vendor;

public class JpaDataProvider implements IDataProvider {

	private Session session;
	private Transaction tx;
	
	public JpaDataProvider() {
		session = HibernateUtil.getSessionFactory().openSession();
		if (session.getTransaction() != null
	            && session.getTransaction().isActive()) {
			tx = session.getTransaction();
		} else {
			tx = session.beginTransaction();
		}
	}
	
	@Override
	public User authenticate(String name, String password) {
		Query query = session.createQuery("from User where uname=:uname and password=:password");
		query.setString("uname", name);
        MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update(password.getBytes());
			query.setParameter("password", messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        
        if(query.list().size() != 1) {
        	return null;
        }
        
        return (User) query.list().get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getOtherUsers() {
		User user = (User) VaadinSession.getCurrent().getAttribute(
				User.class.getName());
		Query query = session.createQuery("from User where uname!=:uname");
		query.setString("uname", user.getUname());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> getVendors() {
		Query query = session.createQuery("from Vendor");
		return query.list();
	}

	@Override
	public boolean deleteObjects(Collection<Object> objects) {
		boolean success = true;
		Iterator<Object> it = objects.iterator();
		while(it.hasNext()) {
			try {
				session.delete(it.next());
				tx.commit();
			} catch (HibernateException ex) {
				success = false;
			}
		}
		return success;
	}

	@Override
	public boolean deleteObject(Object object) {
		boolean success = true;
		try {
			session.delete(object);
			tx.commit();
		} catch (HibernateException ex) {
			success = false;
		}
		return success;
	}

	@Override
	public boolean saveObject(Object object) {
		boolean success = true;
		try {
			session.save(object);
			tx.commit();
		} catch (HibernateException ex) {
			success = false;
		}
		return success;
	}
}
