package cn.wymo.etc.installer;

import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.wymo.etc.common.model.Assignment;
import cn.wymo.etc.common.model.Role;
import cn.wymo.etc.common.model.User;

/**
 * Hello world!
 *
 */
public class App 
{
	static final Logger logger = Logger.getLogger(App.class);
	static Console console = System.console();
	
	static boolean bInitDB = false;
	static String host = "127.0.0.1";
	static String port = "3306";
	static String name = "etc";
	static String user = "etc";
	static String password = "";
	static String confirm;
	
	@SuppressWarnings("deprecation")
	public static void main( String[] args ) {
		String str = "";
		console = System.console();
		if (console != null) {
			console.printf("Do you want to re-initialize database? [Y/n] \n");
	        str = console.readLine();
	        if("Y".equals(str)) {
	        	bInitDB = true;
	        }
	        console.printf("Enter hostname or IP of database server? [127.0.0.1]\n");
			str = console.readLine();
			if(!str.isEmpty()) {
				host = str;
			}
			
			console.printf("Enter DB Port? [3306]\n");
			str = console.readLine();
			if(!str.isEmpty()) {
				port = str;
			}
			
			console.printf("Enter DB name? [etc]\n");
			str = console.readLine();
			if(!str.isEmpty()) {
				name = str;
			}
			
			console.printf("Enter username? [etc]\n");
			str = console.readLine();
			if(!str.isEmpty()) {
				user = str;
			}
			
			while(password.isEmpty()) {
				char passwordArray[] = console.readPassword("Enter your secret password: ");
				password = new String(passwordArray);
				if(password.isEmpty()) {
					console.printf("Empty!\n");
				}
			}

			if(bInitDB) {
				while(!password.equals(confirm)) {
					char confirmArray[] = console.readPassword("Confirm your secret password: ");
					confirm = new String(confirmArray);
					if(!password.equals(confirm)) {
						console.printf("Not match!\n");
					}
				}
	        }
			
	        printA();
	        console.printf("Do you confirm info above? [Y/n] \n");
	        str = console.readLine();
	        if(!"Y".equals(str)) {
	        	System.exit(0);
	        }
	        
	        Configuration cfg = new Configuration().configure();
	        cfg.setProperty("hibernate.connection.url", "jdbc:mysql://"+host+"/"+name);
	        cfg.setProperty("hibernate.connection.username", user);
	        cfg.setProperty("hibernate.connection.password", password);
	        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
	        try {
	        	Iterator<Assignment> it;
	        	SessionFactory sf = cfg.buildSessionFactory();
	        	Session session = sf.openSession();
				Transaction tx = session.beginTransaction();
				while(true) {
		        	console.printf("Do you want to add admin user? [Y/n] \n");
		        	str = console.readLine();
			        if(!"Y".equals(str)) {
			        	break;
			        }
			        
			        user = "";
			        while(user.isEmpty()) {
			        	 console.printf("Enter username? \n");
			        	 user = console.readLine();
			        	 if(user.isEmpty()) {
			        		 console.printf("Empty!\n");
			        	 }
			        }
			        
			        password = "";
					while(password.isEmpty()) {
						char passwordArray[] = console.readPassword("Enter your secret password: ");
						password = new String(passwordArray);
						if(password.isEmpty()) {
							console.printf("Empty!\n");
						}
					}
					
					confirm = "";
					while(!password.equals(confirm)) {
						char confirmArray[] = console.readPassword("Confirm your secret password: ");
						confirm = new String(confirmArray);
						if(!password.equals(confirm)) {
							console.printf("Not match!\n");
						}
					}
					
					User admin = new User();
					admin.addAssignment(Role.ADMINISTRATOR);
					
					MessageDigest messageDigest;
					try {
						messageDigest = MessageDigest.getInstance("SHA1");
						messageDigest.update(password.getBytes());
						admin.setUname(user);
						admin.setPassword(messageDigest.digest());
						session.save(admin);
						it = admin.getAssignments().iterator();
						while(it.hasNext()) {
							session.save(it.next());
						}
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
		        }
				
				MessageDigest messageDigest = null;
				try {
					messageDigest = MessageDigest.getInstance("SHA1");
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				
				if(messageDigest != null) {
					User a = new User();
					a.setUname("a");
					a.addAssignment(Role.ADMINISTRATOR);
					messageDigest.update("a".getBytes());
					a.setPassword(messageDigest.digest());
					session.save(a);
					it = a.getAssignments().iterator();
					while(it.hasNext()) {
						session.save(it.next());
					}
					
					User v = new User();
					v.setUname("v");
					v.addAssignment(Role.VENDOR);
					messageDigest.update("v".getBytes());
					v.setPassword(messageDigest.digest());
					session.save(v);
					it = v.getAssignments().iterator();
					while(it.hasNext()) {
						session.save(it.next());
					}
					
					
					User u = new User();
					u.setUname("u");
					messageDigest.update("u".getBytes());
					u.setPassword(messageDigest.digest());
					session.save(u);
					it = u.getAssignments().iterator();
					while(it.hasNext()) {
						session.save(it.next());
					}
				}

				tx.commit();
				session.close();
				sf.close();
	        } catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	        	throw new ExceptionInInitializerError(ex);
	        }
	    }
        
        
	}
	
	
	private static void printA() {
		console.printf("========================\n");
		console.printf("init     : %s\n", bInitDB?"YES":"NO");
		console.printf("db       : mysql://%s:%s/%s\n", host, port, name);
		console.printf("user     : %s\n", user);
		console.printf("========================\n");
	}
}
