package cn.wymo.etc.producerUI.data;

import java.util.Collection;
import java.util.List;

import cn.wymo.etc.common.model.User;
import cn.wymo.etc.common.model.Vendor;

public interface IDataProvider {
	/**
	 * Check user can be authenticated
	 * 
	 * @param name
	 * 		username
	 * @param password
	 * 		password
	 * @return User if authentication is ok.
	 */
	public User authenticate(String name, String password);
	
	public List<User> getOtherUsers();
	public List<Vendor> getVendors();
	
	/**
	 * Delete objects from DB
	 * @param objects
	 * 		objects to delete
	 * @return
	 * 		true the objects are all deleted
	 */
	public boolean deleteObjects(Collection<Object> objects);

	/**
	 * Delete object from DB
	 * @param object
	 * 		object to delete
	 * @return
	 * 		true the object is deleted
	 */
	public boolean deleteObject(Object object);

	/**
	 * Save object into DB
	 * @param object
	 * 		object to save
	 * @return
	 * 		true the object is saved
	 */
	public boolean saveObject(Object object);
}
