package cn.wymo.etc.common.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
public class User {
	@Id @GeneratedValue
	@Column(name = "id")
	private long id;
	@Column(name = "uname")
	private String uname;
	@Column(name = "password")
	private byte[] password;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Assignment> assignments = new HashSet<Assignment>(0);
	@Column(name = "firstName")
    private String firstName;
	@Column(name = "lastName")
    private String lastName;
	@Column(name = "male")
    private boolean male;
	@Column(name = "email")
    private String email;
	@Column(name = "location")
    private String location;
	@Column(name = "phone")
    private String phone;
	@Column(name = "photo")
    private String photo;
	@Column(name = "created_at")
	private Timestamp created_at;
	@Column(name = "last_login_at")
	private Timestamp last_login_at;
	
	public User() {
		super();
	}
	
	public User(String uname, byte[] password) {
		super();
		this.uname = uname;
		this.password = password;
	}
	
	public User(String uname, byte[] password, String firstName, String lastName, boolean male,
			String email, String location, String phone) {
		super();
		this.uname = uname;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.male = male;
		this.email = email;
		this.location = location;
		this.phone = phone;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}
	
	public Set<Assignment> getAssignments() {
		return assignments;
	}
	
	public void addAssignment(Role role) {
		boolean has = false;
		Iterator<Assignment> it = assignments.iterator();
		while(it.hasNext()) {
			if(role.equals(it.next().getRole())) {
				has = true;
				break;
			}
		}
		if(!has) {
			assignments.add(new Assignment(this, role.getName()));
		}
	}
	
	public boolean hasRole(Role role) {
		boolean has = false;
		Iterator<Assignment> it = assignments.iterator();
		while(it.hasNext()) {
			if(role.equals(it.next().getRole())) {
				has = true;
				break;
			}
		}
		return has;
	}

	public String getFirstName() {
		return firstName==null?"名":firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName==null?"姓":lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public String getEmail() {
		return email==null?"邮件":email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location==null?"地址":location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone==null?"电话":phone;
	}

	public void setPhone(String photo) {
		this.photo = photo;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String phone) {
		this.phone = phone;
	}
	
	public Timestamp getCreatedAt() {
		return created_at;
	}
	
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	public Timestamp getLastLoginAt() {
		return last_login_at;
	}
	
	public void setLastLoginAt(Timestamp last_login_at) {
		this.last_login_at = last_login_at;
	}

	public long getId() {
		return id;
	}	
}
